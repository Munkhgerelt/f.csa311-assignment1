package com.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.flashcard.model.FlashCard;

public class FlashCardTest {
    @Test
    public void testFlashCardCreation() {
        FlashCard card = new FlashCard("Q", "A");
        assertEquals("Q", card.getQuestion(false));
        assertEquals("A", card.getAnswer(false));
    }
    
    @Test
    public void testInvertedCard() {
        FlashCard card = new FlashCard("Q", "A");
        assertEquals("A", card.getQuestion(true));
        assertEquals("Q", card.getAnswer(true));
    }
}