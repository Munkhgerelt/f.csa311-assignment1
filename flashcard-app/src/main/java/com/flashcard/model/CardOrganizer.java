package com.flashcard.model;

import java.util.List;

public interface CardOrganizer {
    /**
     * Organizes a list of flash cards according to specific criteria
     * @param cards The list of cards to organize
     * @return A new list containing the organized cards
     */
    List<FlashCard> organize(List<FlashCard> cards);
}
