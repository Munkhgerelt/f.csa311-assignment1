package com.flashcard.model;

import java.util.Collections;
import java.util.List;

public class RandomOrganizer implements CardOrganizer {
    @Override
    public List<FlashCard> organize(List<FlashCard> cards) {
        List<FlashCard> shuffled = new java.util.ArrayList<>(cards);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}
