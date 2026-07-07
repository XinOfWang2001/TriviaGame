package com.trivia2.entities;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public class Trivia {
    public String question;
    public String correctAnswer;
    public List<String> choices;

    public Trivia(String question, String CorrectAnswer, List<String> Choices){
        this.question = question;
        this.correctAnswer = CorrectAnswer;
        this.choices = Choices;
    }


    public Question getQuestion() {
        return new Question(this.question, choices);
    }

    public boolean ValidateAnswer(Answer answer) throws InvalidAttributeValueException {
        if (!choices.contains(answer.answer())){
            throw new InvalidAttributeValueException("Invalid answer, because the answer is not selectable");
        }
        return correctAnswer.contentEquals(answer.answer());
    }
}
