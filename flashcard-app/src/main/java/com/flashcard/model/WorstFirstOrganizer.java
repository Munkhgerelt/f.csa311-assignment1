package com.flashcard.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WorstFirstOrganizer implements CardOrganizer {
    @Override
    public List<FlashCard> organize(List<FlashCard> cards) {
        return cards.stream()
                .sorted(Comparator.comparingDouble(this::getIncorrectRatio).reversed())
                .collect(Collectors.toList());
    }
    
    private double getIncorrectRatio(FlashCard card) {
        int total = card.getTotalAttempts();
        return total == 0 ? 0 : (double) card.getIncorrectCount() / total;
    }
}
