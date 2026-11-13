package com.mnboot.scraper;


import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements {@code HtmlDatabase} using {@code ArrayList<Document>} as storing
 *
 * @see com.mnboot.scraper.HtmlDatabase
 */
public class HtmlDatabaseInMemory implements HtmlDatabase{
    private ArrayList<HtmlDocument> arrDb;

    /**
     * gets all documents in a database
     *
     * @return List of all documents
     */
    @Override
    public List<HtmlDocument> getAll() {
        //Lists are unmodifiable so no need to clone
        return arrDb;
    }

    /**
     * insert document into Database
     *
     * @param doc HTML document to be inserted
     * @return always returns {@code true}
     */
    @Override
    public boolean insert(HtmlDocument doc) {
        arrDb.add(doc);
        return true; //ArrayList always works :D
    }

}
