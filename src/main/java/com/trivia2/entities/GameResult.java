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

    public void IncreaseInvalid(){
        invalidAnswers++;
    }
    public void IncreaseCorrect(){
        correctlyAnswered++;
    }

    public void IncreaseIncorrect(){
        incorrectlyAnswered++;
    }
}
