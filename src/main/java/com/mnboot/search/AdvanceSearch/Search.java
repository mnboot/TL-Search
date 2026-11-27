package com.mnboot.search.AdvanceSearch;

import com.mnboot.scraper.HtmlDocument;
import com.mnboot.scraper.Scraper;

import java.util.*;

public final class Search {
    private final Scraper scraper;
    private List<HtmlDocument> DbList = List.of();


    public void updateDB(){
        DbList = scraper.getHtmlDb().getAll();
    }

    public Search(Scraper scraper){
        this.scraper = scraper;
        updateDB();
    }



    /**
     * convert string in List of words lowercased and removes unnecessary punctuations
     * <br/>
     * Example: <br/>
     * {@code
     *      String text = """
     *      Hello,
     *      World and hi!!!
     *      """;
     *      tokenize(text); // ["hello", "world", "and", "hi"], no white, all lowercase, and no punchuations
     * }
     * @param text text to tokenized
     * @return list of words from the {@code text} without punctuations
     *
     */
    private static List<String> tokenize(String text){

        return List.of(text.toLowerCase(Locale.ROOT) // use universal lowercase (fun fact: in Turkish, letter I is lowercased ı, not i)
                        .replaceAll("[^\\p{L}\\p{N}]", " ") // replace non-Letter/non-Number to " "
                        .split("\\s+")); // convert white space into elements (type: String[])
    }

    /**
     * calculate Term Frequency (TF)<br/>
     * that is: how many times did a word appeared in the text divided by the words
     * used for counting
     * @param term a single word
     * @param text a List of words; usually a sentence from {@link tokenize}
     * @return the {@code term} score: higher means the word is used a lot, lower means the word rarely in the text
     */
    private double calculateTF(String term, List<String> text){

        long frequency = text.stream()
                .filter(term::equals)
                .count();
        return (double) frequency / text.size();
    }

    /**
     * Calculate Inverse Document Frequency (IDF)<br/>
     * it devalues words that seen a lot in documents
     * @param term a word to compare entire database
     * @return the {@code term} score: higher means the word is rare and, thus, likely important, lower means it is a common word and, thus, redundant
     */
    private double calculateIDF(
            String term) {

        long documentFrequency = DbList.stream()
                .filter(document ->
                        tokenize(document.getUrl()).contains(term)
                        || tokenize(document.getTitle()).contains(term)
                        || tokenize(document.getBody()).contains(term)
                ).count();


        return Math.log((1.0 + DbList.size()) / (1.0 + documentFrequency) ) + 1.0;
    }

    /**
     calculate TFIDF of one document, this makes document easy to analyze.</br>
     same as {@link calculateQueryVector} but int calculate documents instead of user input

     @param document document to get score vector
     @return Hashmap of score vector of one document (word as key and number as value)
     */
    private Map<String, Double> calculateDocumentVector(
            HtmlDocument document
    ) {

        // get all words from one document
        List<String> urlTerms = tokenize(document.getUrl());
        List<String> titleTerms = tokenize(document.getTitle());
        List<String> bodyTerms = tokenize(document.getBody());

        // put all words into vocabulary hash set
        Set<String> vocabulary = new HashSet<>();
        vocabulary.addAll(urlTerms);
        vocabulary.addAll(titleTerms);
        vocabulary.addAll(bodyTerms);

        //calculate vector score
        Map<String, Double> vector = new HashMap<>();
        for (String term : vocabulary) {
            //get all TF from the document
            double weightedTF =
                    HtmlDocument.URL_SCORE * calculateTF(term, urlTerms)
                            + HtmlDocument.TITLE_SCORE * calculateTF(term, titleTerms)
                            + HtmlDocument.BODY_SCORE * calculateTF(term, bodyTerms);
            // get all idf from ALL DOCUMENTS
            double idf = calculateIDF(term);

            vector.put(term, weightedTF * idf);
        }

        return vector;
    }

    /**
     calculate TFIDF user search term to be comparable by cosineSimilarity
     same as {@link calculateDocumentVector} but int calculate user input instead of documents
     @param query user-inputted search query
     @return Hashmap of score vector of user query (word as key and number as value)
     */
    private Map<String, Double> calculateQueryVector(String query) {

        List<String> queryTerms = tokenize(query);

        Map<String, Double> vector = new HashMap<>();

        for (String term : new HashSet<>(queryTerms)) {

            double tf = calculateTF(term, queryTerms);
            double idf = calculateIDF(term);

            vector.put(
                    term,
                    tf * idf
            );
        }

        return vector;
    }

    /**
     * compare to vectors for similarity to get score
     * @param queryVector user inputted vector. usually get it from {@link calculateQueryVector}
     * @param documentVector user document vector. usually get it from {@link calculateDocumentVector}
     * @return score that ranges [0.0, 1.0]. the bigger the number means more it matches with user input
     */
    private static double cosineSimilarity(
            Map<String, Double> queryVector,
            Map<String, Double> documentVector
    ) {


        double queryMagnitude = 0.0; // queryMagnitude will equal to sqrt(a^2+b^2+c^2...) where abc... are score of each token in query
        double documentMagnitude = 0.0; // documentMagnitude will equal to sqrt(a^2+b^2+c^2...) where abc... are score of each token in document

        double dotProduct = 0.0; // sum of multiple

        for (double value : queryVector.values()) {
            queryMagnitude += value * value; // square of sums: query a^2 + b^2 + c^2 ...
        }

        for (double value : documentVector.values()) {
            documentMagnitude += value * value; // square of sums: query a^2 + b^2 + c^2 ...
        }

        for (String term : queryVector.keySet()) {
            dotProduct += queryVector.get(term)
                    * documentVector.getOrDefault(term, 0.0);
        }

        double denominator =
                Math.sqrt(queryMagnitude)
                        * Math.sqrt(documentMagnitude);

        return (denominator != 0.0) ? (dotProduct / denominator) : 0.0;
    }


    public PriorityQueue<HtmlDocument> search(String query, int limit) {

        Map<HtmlDocument, Double> scores = new HashMap<>();

        PriorityQueue<HtmlDocument> queue = new PriorityQueue<>(
                Comparator.comparingDouble(scores::get).reversed()
        );

        if (query == null || query.isBlank() || limit <= 0 ||
                DbList == null || DbList.isEmpty()) {
            return queue;
        }

        Map<String, Double> queryVector =
                calculateQueryVector(query);

        for (HtmlDocument document : DbList) {
            Map<String, Double> documentVector =
                    calculateDocumentVector(document);

            double score = cosineSimilarity(queryVector, documentVector);

            if (score > 0) {
                scores.put(document, score);
                queue.offer(document);

                if (queue.size() > limit) {
                    queue.poll();
                }
            }
        }

        return queue;
    }
}
