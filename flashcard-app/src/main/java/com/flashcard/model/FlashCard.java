package com.flashcard.model;

public class FlashCard {
    private final String question;
    private final String answer;
    private int correctCount = 0;
    private int incorrectCount = 0;
    private boolean lastCorrect = false;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(boolean inverted) {
        return inverted ? answer : question;
    }

    public String getAnswer(boolean inverted) {
        return inverted ? question : answer;
    }

    public void markCorrect() {
        correctCount++;
        lastCorrect = true;
    }

    public void markIncorrect() {
        incorrectCount++;
        lastCorrect = false;
    }

    public boolean wasLastCorrect() {
        return lastCorrect;
    }

    public int getTotalAttempts() {
        return correctCount + incorrectCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }
    
    public int getIncorrectCount() {
        return incorrectCount;
    }

    public void resetLastRound() {
        this.lastCorrect = false;
    }
    
    public int getAttemptCount() {
        return correctCount + incorrectCount;
    }
}
