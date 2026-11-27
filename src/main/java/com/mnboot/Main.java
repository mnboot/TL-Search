package com.mnboot;


import com.mnboot.TUI.TUI;
import com.mnboot.scraper.HtmlDatabaseInMemory;
import com.mnboot.scraper.HtmlDocument;
import com.mnboot.scraper.Scraper;

/**
 * Entry point to the program
 */
public class Main {

    public static void main(String[] args) {
        TUI tui = new TUI();
        tui.start();
    }


    public static void runScraperAndPrintBody(String urlString) {
        try {
            // 1. Initialize your database and scraper
            HtmlDatabaseInMemory db = new HtmlDatabaseInMemory();
            Scraper scraper = new Scraper(db);

            // 2. Call the correct method: getHtmlFromURL
            String targetUrl = "https://example.com"; // Replace with your URL
            HtmlDocument document = scraper.getHtmlFromURL(targetUrl);

            // 3. Print the body data
            if (document != null) {
                System.out.println("--- WEBSITE BODY DATA ---");
                System.out.println(document.getBody());
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
