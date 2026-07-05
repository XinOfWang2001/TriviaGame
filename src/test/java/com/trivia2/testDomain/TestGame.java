package com.trivia2.testDomain;

import com.trivia2.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestGame {

    private ArrayList<Trivia> generateQuestions(){
        List<String> choicesFirst = new ArrayList<>(Arrays.asList("True", "False"));
        List<String> choicesSecond = new ArrayList<>(Arrays.asList("Amsterdam", "Rotterdam", "The Hague", "Utrecht"));

        List<String> choicesThird = new ArrayList<>(Arrays.asList("1939", "1937", "1941"));

        Trivia firstQuestion = new Trivia("Is it true that cookies are sweet?", "True", choicesFirst);
        Trivia secondQuestion = new Trivia("What is the capital of the Netherlands?", "The Hague", choicesSecond);
        Trivia thirdQuestion = new Trivia("Which year started the second world war?", "1939", choicesThird);
        return new ArrayList<>(Arrays.asList(firstQuestion, secondQuestion, thirdQuestion));
    }
    @Test
    public void TestMappingTrivia(){
        // Arrange
        int amountQuestions = 3;
        int correctAnswers = 2;
        int incorrectAnswers = 1;
        ArrayList<Trivia> questions = new ArrayList<>(generateQuestions());
        ArrayList<Answer> submission = new ArrayList<>(
                Arrays.asList(
                        new Answer("Is it true that cookies are sweet?", "True"),
                        new Answer("What is the capital of the Netherlands?", "Amsterdam"),
                        new Answer("Which year started the second world war?", "1939")
                )
        );

        Game game = new Game();
        game.setQuestions(questions);

        // Act
        GameResult result = game.SubmitAnswers(submission);

        // Assert
        Assertions.assertEquals(amountQuestions, game.getQuestions().size());
        Assertions.assertEquals(correctAnswers, result.correctlyAnswered);
        Assertions.assertEquals(incorrectAnswers, result.incorrectlyAnswered);
        Assertions.assertEquals(0, result.invalidAnswers);
    }

    // Test answer
    @Test
    public void TestInvalidQuestionsInSubmissions(){
        int amountQuestions = 3;
        int correctAnswers = 2;
        int incorrectAnswers = 0;
        int invalidAnswers = 1;
        ArrayList<Trivia> questions = new ArrayList<>(generateQuestions());
        ArrayList<Answer> submission = new ArrayList<>(
                Arrays.asList(
                        new Answer("Is it true that cookies are sweet?", "True"),
                        new Answer("What is the capital of the Netherlands?", "The Hague"),
                        new Answer("Which year started the second world war?", "1931")
                )
        );
        Game game = new Game();
        game.setQuestions(questions);

        // Act
        GameResult result = game.SubmitAnswers(submission);

        // Assert
        Assertions.assertEquals(amountQuestions, game.getQuestions().size());
        Assertions.assertEquals(correctAnswers, result.correctlyAnswered);
        Assertions.assertEquals(incorrectAnswers, result.incorrectlyAnswered);
        Assertions.assertEquals(invalidAnswers, result.invalidAnswers);
    }

    @Test
    public void TestFullyCorrectSubmissions(){
        // Arrange
        int amountQuestions = 3;
        int correctAnswers = 3;
        int incorrectAnswers = 0;
        int invalidAnswers = 0;

        ArrayList<Trivia> questions = new ArrayList<>(generateQuestions());
        ArrayList<Answer> submission = new ArrayList<>(
                Arrays.asList(
                        new Answer("Is it true that cookies are sweet?", "True"),
                        new Answer("What is the capital of the Netherlands?", "The Hague"),
                        new Answer("Which year started the second world war?", "1939")
                )
        );
        Game game = new Game();
        game.setQuestions(questions);

        // Act
        GameResult result = game.SubmitAnswers(submission);

        // Assert
        Assertions.assertEquals(amountQuestions, game.getQuestions().size());
        Assertions.assertEquals(correctAnswers, result.correctlyAnswered);
        Assertions.assertEquals(incorrectAnswers, result.incorrectlyAnswered);
        Assertions.assertEquals(invalidAnswers, result.invalidAnswers);
    }
}
