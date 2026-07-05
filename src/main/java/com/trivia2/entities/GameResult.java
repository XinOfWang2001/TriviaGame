package com.trivia2.entities;

public class GameResult {
    public int invalidAnswers;
    public int correctlyAnswered;
    public int incorrectlyAnswered;

    public GameResult(){
        invalidAnswers = 0;
        incorrectlyAnswered = 0;
        correctlyAnswered = 0;
    }

    public void IncreaseInvalide(){
        invalidAnswers++;
    }
    public void IncreaseCorrect(){
        correctlyAnswered++;
    }

    public void IncreaseIncorrect(){
        incorrectlyAnswered++;
    }

    public int getCorrectlyAnswered() {
        return correctlyAnswered;
    }

    public int getInvalidAnswers() {
        return invalidAnswers;
    }

    public int getIncorrectlyAnswered() {
        return incorrectlyAnswered;
    }
}
