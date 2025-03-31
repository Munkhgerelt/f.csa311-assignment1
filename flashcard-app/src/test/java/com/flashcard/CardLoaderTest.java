package com.flashcard;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.flashcard.model.FlashCard;
import com.flashcard.service.CardLoader;

public class CardLoaderTest {
    @Test
    public void testLoadCards() throws Exception {
        CardLoader loader = new CardLoader();
        List<FlashCard> cards = loader.loadCards("src/test/resources/test-cards.txt");
        assertEquals(2, cards.size());
        assertEquals("Test Q1", cards.get(0).getQuestion(false));
    }
}