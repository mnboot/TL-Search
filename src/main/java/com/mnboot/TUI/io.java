package com.mnboot.TUI;

import java.util.Scanner;

/**
 * Custom Input/Output helper class for handling console interactions.
 */
public class io {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Prints a message to the console without a newline.
     */
    public static void print(String message) {
        System.out.print(message);
    }

    /**
     * Prints a message to the console with a newline.
     */
    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints an empty line to the console.
     */
    public static void println() {
        System.out.println();
    }

    /**
     * Reads a full line of text entered by the user from the console.
     *
     * @return The user's input as a String
     */
    public static String readln() {
        return SCANNER.nextLine();
    }
}
