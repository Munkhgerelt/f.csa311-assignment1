package com.flashcard;

import com.flashcard.config.Config;
import com.flashcard.controller.FlashCardController;

public class Main {
    public static void main(String[] args) {
        // Show help and exit if --help is requested
        if (args.length > 0 && args[0].equals("--help")) {
            Config.showHelp();
            System.exit(0);
        }
        
        try {
            Config config = new Config(args);
            FlashCardController controller = new FlashCardController(config);
            controller.start();
        } catch (IllegalArgumentException e) {
            System.err.println("\nERROR: " + e.getMessage());
            Config.showHelp();
            System.exit(1);
        }
    }
}
