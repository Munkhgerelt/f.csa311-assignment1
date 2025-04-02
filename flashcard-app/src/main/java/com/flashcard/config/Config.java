package com.flashcard.config;

import java.util.Arrays;
import java.util.List;

public class Config {
    private String cardsFile;
    private String order;
    private int repetitions;
    private boolean invertCards;

    private static final List<String> VALID_ORDERS = Arrays.asList("random", "worst-first", "recent-mistakes-first");

    public Config(String[] args) throws IllegalArgumentException {
        // Default values
        this.cardsFile = "cards.txt";
        this.order = "random";
        this.repetitions = 1;
        this.invertCards = false;

        parseArguments(args);
        validate();
    }

    private void parseArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--help")) {
                showHelp();
                System.exit(0);
            } else if (args[i].equals("--order") && i + 1 < args.length) {
                this.order = args[++i];
            } else if (args[i].equals("--repetitions") && i + 1 < args.length) {
                try {
                    this.repetitions = Integer.parseInt(args[++i]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number for repetitions");
                }
            } else if (args[i].equals("--invertCards")) {
                this.invertCards = true;
            } else if (i == 0) {
                this.cardsFile = args[i];
            }
        }
    }

    private void validate() {
        if (!VALID_ORDERS.contains(order)) {
            throw new IllegalArgumentException("Invalid order specified. Valid options are: " + VALID_ORDERS);
        }
        if (repetitions < 1 || repetitions > 10) {
            throw new IllegalArgumentException("Repetitions must be between 1 and 10");
        }
    }

    public void setOrder(String newOrder) {
        if (VALID_ORDERS.contains(newOrder.toLowerCase())) {
            this.order = newOrder.toLowerCase();
            System.out.println("Order set to: " + this.order);
        } else {
            System.out.println("Invalid order. Keeping the current order: " + this.order);
        }
    }

    public void changeRepetition(int newRepetitions) {
        if (newRepetitions >= 1 && newRepetitions <= 10) {
            this.repetitions = newRepetitions;
            System.out.println("Repetition count set to: " + this.repetitions);
        } else {
            System.out.println("Invalid repetition count. Please enter a value between 1 and 10.");
        }
    }
    
    public void invert() {
        this.invertCards = !this.invertCards;  // Toggle the value (true to false, or false to true)
        System.out.println("Inversion of cards set to: " + (this.invertCards ? "ON" : "OFF"));
    }
    
    public static void showHelp() {
        System.out.println("\nFlashcard Application - Command Line Usage");
        System.out.println("========================================");
        System.out.println("\nBasic Command:");
        System.out.println("  java -jar target\\flashcard-app-1.0-SNAPSHOT.jar <cards-file> [options]");
        
        System.out.println("\nExample Command:");
        System.out.println("  java -jar target\\flashcard-app-1.0-SNAPSHOT.jar cards.txt --order worst-first --repetitions 3");
        
        System.out.println("\nRequired:");
        System.out.printf("  %-25s %s%n", "<cards-file>", "Path to flashcard file (e.g., cards.txt)");
        
        System.out.println("\nOptions:");
        System.out.printf("  %-25s %s%n", "--help", "Show this help message");
        System.out.printf("  %-25s %s%n", "--order <order>", "Card sorting method");
        System.out.printf("  %-25s %s%n", "", "  random: Shuffle randomly (default)");
        System.out.printf("  %-25s %s%n", "", "  worst-first: Sort by hardest cards");
        System.out.printf("  %-25s %s%n", "", "  recent-mistakes-first: Prioritize recent mistakes");
        System.out.printf("  %-25s %s%n", "--repetitions <num>", "Practice rounds (1-10, default: 1)");
        System.out.printf("  %-25s %s%n", "--invertCards", "Show answers as questions (default: false)");
        
        System.out.println("\nFile Format:");
        System.out.println("  Question|Answer (one per line)");
        System.out.println("  Example: \"Capital of France|Paris\"");
    }

    // Getters
    public String getCardsFile() { return cardsFile; }
    public String getOrder() { return order; }
    public int getRepetitions() { return repetitions; }
    public boolean isInvertCards() { return invertCards; }
}
