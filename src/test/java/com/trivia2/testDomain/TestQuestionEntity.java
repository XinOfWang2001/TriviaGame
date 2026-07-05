package com.trivia2.testDomain;

import com.trivia2.entities.Answer;
import com.trivia2.entities.Trivia;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestQuestionEntity {

    @Test
    public void TestNonValidAnswerQuestion() {
        String ask = "What is 1 + 1 ?";
        String CorrectAnswer = "Two";
        String myAnswer = "TwentyOne";
        Answer answer = new Answer(ask, myAnswer);
        List<String> choices = new ArrayList<>();
        choices.add("One");
        choices.add("Two");
        choices.add("Three");
        Trivia trivia = new Trivia(ask, CorrectAnswer, choices);
        assertThrows(InvalidAttributeValueException.class, ()-> trivia.ValidateAnswer(answer));
    }

    @Test
    public void TestCorrectAnswerToQuestion() throws InvalidAttributeValueException {
        String ask = "What is 1 + 1 ?";
        String CorrectAnswer = "Two";
        String myAnswer = "Two";
        Answer answer = new Answer(ask, myAnswer);
        List<String> choices = new ArrayList<>();
        choices.add("One");
        choices.add("Two");
        choices.add("Three");
        Trivia trivia = new Trivia(ask, CorrectAnswer, choices);

        boolean result = trivia.ValidateAnswer(answer);
        assertTrue(result);
    }

    @Test
    public void TestIncorrectAnswerToQuestion() throws InvalidAttributeValueException {
        String ask = "What is 1 + 1 ?";
        String CorrectAnswer = "Two";
        Answer answer = new Answer(ask,"Three");
        List<String> choices = new ArrayList<>();
        choices.add("One");
        choices.add("Two");
        choices.add("Three");

        Trivia trivia = new Trivia(ask, CorrectAnswer, choices);

        boolean result = trivia.ValidateAnswer(answer);
        assertFalse(result);
    }
}
