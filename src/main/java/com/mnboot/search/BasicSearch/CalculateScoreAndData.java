package com.mnboot.search.BasicSearch;

import com.mnboot.scraper.HtmlDocument;

public class CalculateScoreAndData {

    /**
     *
     * @param doc it gets the current doc to get its URL to process the final score
     * @param word this is the word to find inside URL
     * @return URL final score for a specific word
     */
    private static double getScoreForURL(HtmlDocument doc,String word){

        String text = doc.getUrl();
        double count = 0.0;

        for (String cleanword : text.split("[^a-zA-Z]+")) {
            if (cleanword.equalsIgnoreCase(word)) {
                count = count + HtmlDocument.URL_SCORE;
            }
        }

        return count;

    }

    /**
     *
     * @param doc t gets the current doc to get its Body to process the final score
     * @param word this is the word to find inside Body
     * @return Body final score for a specific word
     */
    private static double getScoreForBody(HtmlDocument doc,String word){

        String text = doc.getBody();
        double count = 0.0;

        for (String c : text.split("\\s+")) {
            String cleanword = c.replaceAll("[^a-zA-Z]", "");

            if(cleanword.equalsIgnoreCase(word))
                count= count + doc.BODY_SCORE;
        }

        return count;

    }

    /**
     *
     * @param doc it gets the current doc to get its Title to process the final score
     * @param word this is the word to find inside Title
     * @return  Title final score for a specific word
     */
    private static double getScoreForTitle(HtmlDocument doc,String word){

        String text = doc.getTitle();
        double count = 0.0;

        for (String c : text.split("\\s+")) {
            String cleanword = c.replaceAll("[^a-zA-Z]", "");

            if(cleanword.equalsIgnoreCase(word))
                count= count + doc.TITLE_SCORE;
        }

        return count;

    }

    /**
     *
     * @param doc gettting the current doc to analuze its body, title, and URL to determine the final score
     * @param word this is the word to find on body, title, and URL
     * @return it returns the combined score of body, title, and URL
     */

    public static double getScore(HtmlDocument doc, String word){

        double count = 0.0;

        count +=getScoreForURL(doc, word);
        count +=getScoreForTitle(doc, word);
        count +=getScoreForBody(doc, word);

        return count;


    }

    /**
     *  This method aims to extract URL, Body, and few words on Body
     * @param doc its the data that contains URL, Title,and Body,
     * @return final formatted data of document to print on screen
     */
    public static StringBuilder getData(HtmlDocument doc) {
        StringBuilder temp = new StringBuilder();
        temp.append("Title: " + doc.getTitle());
        temp.append("\nURL: " + doc.getUrl());
        temp.append("\nBody: ");


        String body = doc.getBody();
        int count = 0;

        for (int i = 0; i < body.length(); i++) {
            temp.append(body.charAt(i));
            if (Character.isWhitespace(body.charAt(i))) count++;

            if (count == 20) {
                temp.append("...");
                break;
            }

        }

        return temp;
    }
}
