package com.mnboot.scraper;


//TODO: complete Scraping
public class Scraper {

    //TODO:
    private HtmlDatabase HtmlDb;

    /**
     * Create instance of Scraper
     * @param HtmlDb type of HTML database usually {@code HtmlDatabaseInMemoryImpl}
     * @see HtmlDatabaseInMemoryImpl
     */
    Scraper(HtmlDatabase HtmlDb){
        this.HtmlDb = HtmlDb;
    }
}
