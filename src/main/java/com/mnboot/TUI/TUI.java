package com.mnboot.TUI;

import com.mnboot.scraper.HtmlDatabase;
import com.mnboot.scraper.HtmlDatabaseInMemory;
import com.mnboot.scraper.HtmlDocument;
import com.mnboot.scraper.Scraper;
import com.mnboot.search.AdvanceSearch.Search;
import com.mnboot.search.Searchable;

import java.util.List;
import java.util.Objects;

public class TUI extends AbstractTUI{


    private final HtmlDatabase db = new HtmlDatabaseInMemory();
    private final Scraper scraper = new Scraper(db);
    private final Searchable search = new Search(scraper);
    private final int MAXIMUM_QUERY_RESULT = 10;



    final int MAXIMUM_BODY_LIMIT = 75;
    private String limitText(String text){
        if(text.length() > MAXIMUM_BODY_LIMIT){
            return text.substring(0, MAXIMUM_BODY_LIMIT) + "...";
        }
        return text;
    }
    private void printAllWeb(List<HtmlDocument> searchResult){
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println(i+1 + ": ");
            System.out.println("---------------------");
            System.out.println("URL:   "+ searchResult.get(i).getUrl());
            System.out.println("Title: "+ searchResult.get(i).getTitle());
            System.out.println("Body:  "+ limitText(searchResult.get(i).getBody()));
            System.out.println("--------------------");
        }
    }

    private static final String GREETING = """
             _       __     __                             __           ________       _____                      __ \s
            | |     / /__  / /________  ____ ___  ___     / /_____     /_  __/ /      / ___/___  ____ ___________/ /_\s
            | | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\   / __/ __ \\     / / / /       \\__ \\/ _ \\/ __ `/ ___/ ___/ __ \\
            | |/ |/ /  __/ / /__/ /_/ / / / / / /  __/  / /_/ /_/ /    / / / /___    ___/ /  __/ /_/ / /  / /__/ / / /
            |__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/   \\__/\\____/    /_/ /_____/   /____/\\___/\\__,_/_/   \\___/_/ /_/\s
            
            """;

    /**
     * entry point, welcome user and gives option to either use Scraper or Search
     */
    @Override
    @SuppressWarnings("InfiniteLoopStatement") // my IDE complains about infinite loops
    public void start() {
        System.out.println(GREETING);
        while (true) {
            System.out.println("Choose 1 for scraping. 2 for searching");
            int userOption = getInputStart();
            if (userOption == -1) {
                System.err.println("Error: Numbers only");
            } else {
                switch (userOption) {
                    case 1 -> scraper();
                    case 2 -> searching();
                    default -> System.err.println("Error: no option for input " + userOption);
                }
            }
        }
    }

    /**
     * get user input. Use it only on {@code start()} method
     * @see
     * @return input integer. -1 if user entered String
     */
    private int getInputStart(){
        System.out.print("=> ");
        String option = IO.readln();
        try {
            return Integer.parseInt(option);
        } catch (NumberFormatException nfe){
            return -1;
        }

    }

    /**
     * TUI for Scraper
     */
    @Override
    protected void scraper() {
        System.out.print("Enter website URL to scrape: ");
        String url = IO.readln();

        try {
            // This fetches the page and stores it in the in-memory database
            HtmlDocument document = scraper.getHtmlFromURL(url);

            if (document != null) {
                System.out.println("\n========================================");
                System.out.println("       STORED IN PROGRAM MEMORY         ");
                System.out.println("========================================");
                System.out.println("URL   : " + document.getUrl());
                System.out.println("Head  : " + document.getTitle());
                System.out.println("Body  : " + document.getBody());
                System.out.println("========================================\n");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    /**
     * TUI for Search
     */
    @Override
    protected void searching() {
        String input;
        do {
            System.out.print("Enter URL (-1 for exit): ");
            input = IO.readln();
            if(!Objects.equals(input, "-1")){
                System.out.println("Searching for \"" + input + "\"");
                List<HtmlDocument> result = search.search(input, MAXIMUM_BODY_LIMIT);
                printAllWeb(result);
            }
        }while (!Objects.equals(input, "-1"));


    }



    /*@Override
    protected void search(){

     */




    /**
     * TUI for Search
     */
    /*@Override
    protected void search() {

        System.out.print("Search for the term: ");
        String word = IO.readln();

        // Let Ranking handle gathering and scoring all documents from the scraper instance
        ArrayList<StoreDataAndScore> results = Ranking.StoreDataAndScore(scraper.getHtmlDb(), word);

        if (results != null && !results.isEmpty()) {
            // Sort the results in-place using QuickSort (descending order by score)
            Ranking.quickSort(results, 0, results.size() - 1);

            // Print the ranked results to the console
            Ranking.PrintALlDatas(results);
        } else {
            System.out.println("No results found for: " + word);
        }

    }
     */
}
