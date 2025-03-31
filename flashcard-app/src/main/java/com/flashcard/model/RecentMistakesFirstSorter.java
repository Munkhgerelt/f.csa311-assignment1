package com.flashcard.model;

import java.util.ArrayList;
import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer {
    @Override
    public List<FlashCard> organize(List<FlashCard> cards) {
        List<FlashCard> organized = new ArrayList<>(cards.size());
        List<FlashCard> correctCards = new ArrayList<>();
        
        // Separate cards answered incorrectly last time
        for (FlashCard card : cards) {
            if (card.wasLastCorrect()) {
                correctCards.add(card);
            } else {
                organized.add(card);
            }
        }
        
        // Add correctly answered cards at the end
        organized.addAll(correctCards);
        return organized;
    }
}