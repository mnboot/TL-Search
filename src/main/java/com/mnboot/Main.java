package com.mnboot;


import com.mnboot.TUI.TUI;

/**
 * Entry point to the program
 */
public class Main {

    static void main() {
        // IMPORTANT: DO NOT IMPLEMENT BUSINESS LOGIC ON MAIN
        // WRITE ONLY ONE LINE: A STATIC METHOD ON THE `TUI` PACKAGE THAT RUNS WHOLE TUI
        TUI tui = new TUI();
        tui.start();
    }
}
