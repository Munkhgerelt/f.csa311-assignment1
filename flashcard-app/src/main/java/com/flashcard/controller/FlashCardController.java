package com.flashcard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.flashcard.config.Config;
import com.flashcard.model.CardOrganizer;
import com.flashcard.model.FlashCard;
import com.flashcard.service.AchievementTracker;
import com.flashcard.service.CardLoader;
import com.flashcard.service.OrganizerFactory;

public class FlashCardController {
    private final Config config;
    private final Scanner scanner;
    private List<FlashCard> cards;
    private final AchievementTracker achievementTracker;

    public FlashCardController(Config config) {
        this.config = config;
        this.scanner = new Scanner(System.in);
        this.achievementTracker = new AchievementTracker();
    }

    public void start() {
        try (scanner) {
            loadCards();
            showMainMenu();
        } catch (IOException e) {
            System.err.println("Error loading cards: " + e.getMessage());
            System.exit(1);
        }
    }

    private void loadCards() throws IOException {
        CardLoader loader = new CardLoader();
        this.cards = loader.loadCards(config.getCardsFile());
    }

    private void showMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Review cards");
            System.out.println("2. List all cards");
            System.out.println("3. Show statistics");
            System.out.println("4. Show achievements");
            System.out.println("5. Show options help");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> reviewCards();
                case "2" -> listAllCards();
                case "3" -> showStats();
                case "4" -> achievementTracker.printAchievements();
                case "5" -> Config.showHelp();
                case "6" -> running = false;
                default -> System.out.println("Invalid choice. Please enter 1-6.");
            }
        }
    }

    private void reviewCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards available to review.");
            return;
        }
        
        CardOrganizer organizer = OrganizerFactory.createOrganizer(config.getOrder());
        List<FlashCard> cardsToReview = organizer.organize(cards);
        
        System.out.printf("\nReviewing %d cards (%s order, %d repetitions)\n",
                cards.size(), config.getOrder(), config.getRepetitions());
        
        long startTime = System.currentTimeMillis();
        
        for (int rep = 0; rep < config.getRepetitions(); rep++) {
            System.out.printf("\n--- Repetition %d of %d ---\n", rep + 1, config.getRepetitions());
            
            for (FlashCard card : cardsToReview) {
                System.out.println("\nQuestion: " + card.getQuestion(config.isInvertCards()));
                System.out.print("Your answer: ");
                String answer = scanner.nextLine();
                
                if (answer.equalsIgnoreCase(card.getAnswer(config.isInvertCards()))) {
                    System.out.println("Correct!");
                    card.markCorrect();
                } else {
                    System.out.println("Incorrect. The correct answer is: " + 
                            card.getAnswer(config.isInvertCards()));
                    card.markIncorrect();
                }
            }
        }
        
        long endTime = System.currentTimeMillis();
        double averageTime = (endTime - startTime) / 1000.0 / (cards.size() * config.getRepetitions());
        
        achievementTracker.evaluateRound(cardsToReview, averageTime);
        achievementTracker.printAchievements();
        
        System.out.println("\nReview session completed!");
    }

    private void listAllCards() {
        System.out.println("\nAll Flash Cards:");
        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, cards.get(i).getQuestion(config.isInvertCards()));
        }
    }

    private void showStats() {
        System.out.println("\nStatistics:");
        System.out.println("Total cards: " + cards.size());
        System.out.println("Configured order: " + config.getOrder());
        System.out.println("Repetitions: " + config.getRepetitions());
        System.out.println("Inverted cards: " + config.isInvertCards());
    }
}