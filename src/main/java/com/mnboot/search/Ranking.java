package com.mnboot.search;

import com.mnboot.scraper.HtmlDocument;
import com.mnboot.scraper.Scraper;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    /**
     *
     * @param db using database to get all of HTML documents
     * @param word a specific word to use it on storing record
     * @return returns a full arraylist hat each object includes data for output and score
     */
    public static ArrayList<StoreDataAndScore> StoreDataAndScore(Scraper db, String word){

        List<HtmlDocument> allDocs = db.getAllHtmlDocuments();

        ArrayList<StoreDataAndScore> AllData = new ArrayList<>();

        for(HtmlDocument doc: allDocs){
            StoreDataAndScore record = new StoreDataAndScore(doc, word);
            AllData.add(record);
        }

        return AllData;
    }

    public static void quickSort(ArrayList<StoreDataAndScore> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(ArrayList<StoreDataAndScore> list, int low, int high) {
        // Choose the last element as the pivot
        double pivotScore = list.get(high).getScore();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // For descending order: if current score is GREATER than pivot, swap it forward
            if (list.get(j).getScore() > pivotScore) {
                i++;
                // Swap objects in-place
                StoreDataAndScore temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Swap the pivot into its correct position
        StoreDataAndScore temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    /**
     * Printing all HTML documents on descending score value which is determined by searching a word
     * @param allData passing allData to print allData on screen
     */
    public static void PrintALlDatas(ArrayList<StoreDataAndScore> allData){


        for(int i = 0; i < allData.size(); i++) {
            StringBuilder data = allData.get(i).getData();
            double score = allData.get(i).getScore();
            System.out.println("Score: " + score);
            System.out.print(data.toString());
            System.out.println("--------------------------------------------------");
        }


    }


}
