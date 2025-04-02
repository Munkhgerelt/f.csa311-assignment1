package com.flashcard.service;

import com.flashcard.model.CardOrganizer;
import com.flashcard.model.RandomOrganizer;
import com.flashcard.model.RecentMistakesFirstSorter;
import com.flashcard.model.WorstFirstOrganizer;

public class OrganizerFactory {
    public static CardOrganizer createOrganizer(String type) {
        switch (type.toLowerCase()) {
            case "random" -> {
                return new RandomOrganizer();
            }
            case "worst-first" -> {
                return new WorstFirstOrganizer();
            }
            case "recent-mistakes-first" -> {
                return new RecentMistakesFirstSorter();
            }
            default -> throw new IllegalArgumentException("Unknown organizer type: " + type);
        }
    }
}
