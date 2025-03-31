package com.flashcard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flashcard.model.FlashCard;

public class AchievementTracker {
    private final Map<String, Boolean> achievements;
    private final Map<FlashCard, Integer> cardAttempts;
    private final Map<FlashCard, Integer> consecutiveCorrect;

    public AchievementTracker() {
        this.achievements = new HashMap<>();
        this.cardAttempts = new HashMap<>();
        this.consecutiveCorrect = new HashMap<>();
        initializeAchievements();
    }

    private void initializeAchievements() {
        achievements.put("SPEEDY", false);  // Existing achievement
        achievements.put("CORRECT", false);
        achievements.put("REPEAT", false);
        achievements.put("CONFIDENT", false);
    }

    public void evaluateRound(List<FlashCard> cards, double averageTime) {
        // Reset achievements for new round
        achievements.replaceAll((k, v) -> false);
        
        // Check SPEEDY achievement (existing)
        achievements.put("SPEEDY", averageTime < 5.0);
        
        // Check CORRECT achievement
        boolean allCorrect = cards.stream().allMatch(FlashCard::wasLastCorrect);
        achievements.put("CORRECT", allCorrect);
        
        // Check REPEAT and CONFIDENT achievements
        boolean hasRepeat = false;
        boolean hasConfident = false;
        
        for (FlashCard card : cards) {
            // Update attempt counts
            int attempts = cardAttempts.getOrDefault(card, 0) + 1;
            cardAttempts.put(card, attempts);
            
            // Check REPEAT condition
            if (attempts > 5) {
                hasRepeat = true;
            }
            
            // Update consecutive correct count
            int correctStreak = consecutiveCorrect.getOrDefault(card, 0);
            if (card.wasLastCorrect()) {
                correctStreak++;
                consecutiveCorrect.put(card, correctStreak);
                
                // Check CONFIDENT condition
                if (correctStreak >= 3) {
                    hasConfident = true;
                }
            } else {
                consecutiveCorrect.put(card, 0);
            }
        }
        
        achievements.put("REPEAT", hasRepeat);
        achievements.put("CONFIDENT", hasConfident);
    }

    public void printAchievements() {
        System.out.println("\n=== Achievements ===");
        achievements.forEach((name, achieved) -> {
            System.out.printf("[%s] %s%n", achieved ? "X" : " ", name);
        });
    }
}