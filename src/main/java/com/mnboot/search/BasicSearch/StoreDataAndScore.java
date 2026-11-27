package com.mnboot.search.BasicSearch;
import com.mnboot.scraper.HtmlDocument;
import lombok.Getter;

@Getter
public class StoreDataAndScore {
    private StringBuilder data;
    private double score;

    /**
     * This method stores data for output and score for a specefic word in a website on instance variables
     * on this class
     * @param doc using Document to generate data
     * @param word using word to calculate score
     */
    StoreDataAndScore(HtmlDocument doc, String word){
        data = CalculateScoreAndData.getData(doc);
        score = CalculateScoreAndData.getScore(doc,word);
    }


}
