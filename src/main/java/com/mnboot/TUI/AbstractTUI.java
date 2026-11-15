package com.mnboot.TUI;

/**
 * TUI skeleton
 * @implNote Do not write business logic from scratch, only use objects from Scraper and Search
 *
 */
public abstract class AbstractTUI {



    /**
     * entry point, welcome user and gives option to either use Scraper or Search
     */
    abstract public void start();

    /**
     * TUI for Scraper
     */
    abstract protected void scraper();

    /**
     * TUI for Search
     */
    abstract protected void search();


}
