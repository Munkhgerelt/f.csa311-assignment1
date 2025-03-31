package com.flashcard.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.flashcard.model.FlashCard;

public class CardLoader {
    public List<FlashCard> loadCards(String filename) throws IOException {
        Path path = Paths.get(filename);
        
        if (!Files.exists(path)) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
            if (inputStream == null) {
                throw new FileNotFoundException("Cannot find card file: " + filename);
            }
            return readCardsFromStream(inputStream);
        }
        
        return Files.readAllLines(path).stream()
            .filter(line -> !line.trim().isEmpty() && !line.startsWith("#"))
            .map(line -> line.split("\\|", 2))
            .filter(parts -> parts.length == 2)
            .map(parts -> new FlashCard(parts[0].trim(), parts[1].trim()))
            .collect(Collectors.toList());
    }

    private List<FlashCard> readCardsFromStream(InputStream is) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines()
                .filter(line -> !line.trim().isEmpty() && !line.startsWith("#"))
                .map(line -> line.split("\\|", 2))
                .filter(parts -> parts.length == 2)
                .map(parts -> new FlashCard(parts[0].trim(), parts[1].trim()))
                .collect(Collectors.toList());
        }
    }
}