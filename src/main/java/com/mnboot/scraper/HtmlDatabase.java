package com.mnboot.scraper;

import java.util.List;

/**
 * HTML Database skeleton that stores HTML Documents as {@code HtmlDocument} data class
 * @see HtmlDocument
 * @see HtmlDatabaseInMemory
 */
interface HtmlDatabase {
    //TODO: add CRUD operations

    /**
     * gets all documents in a database
     * @return List of all documents
     */
    List<HtmlDocument> getAll();

    /**
     * insert document into Database
     * @param doc HTML document to be inserted
     * @return {@code true} if the operation is successful, false otherwise
     */
    boolean insert(HtmlDocument doc);


}
