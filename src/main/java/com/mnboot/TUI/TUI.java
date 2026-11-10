package com.mnboot.TUI;

public class TUI extends AbstractTUI{

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
    @SuppressWarnings("InfiniteLoopStatement") // my IDE complains about infinite loop
    public void start() {
        IO.println(GREETING);
        while (true) {
            IO.println("Choose 1 for scraping. 2 for searching");
            int userOption = getInputStart();
            if (userOption == -1) {
                System.err.println("Error: Numbers only");
            } else {
                switch (userOption) {
                    case 1 -> scraper();
                    case 2 -> search();
                    default -> System.err.println("Error: no option for input " + userOption);
                }
            }
        }
    }

    /**
     * get user input. Use it only on {@code start()} method
     * @see start
     * @return input integer. -1 if user entered String
     */
    private int getInputStart(){
        IO.print("=> ");
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

    }

    /**
     * TUI for Search
     */
    @Override
    protected void search() {

    }
}
