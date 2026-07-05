package com.trivia2.entities;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public class Trivia {
    public String question;
    public String CorrectAnswer;
    public List<String> Choices;

    public Trivia(String question, String CorrectAnswer, List<String> Choices){
        this.question = question;
        this.CorrectAnswer = CorrectAnswer;
        this.Choices = Choices;
    }

    public String getQuestionKey(){
        return question;
    }

    public Question getQuestion() {
        return new Question(this.question, Choices);
    }

    public boolean ValidateAnswer(Answer answer) throws InvalidAttributeValueException {
        if (!Choices.contains(answer.Answer())){
            throw new InvalidAttributeValueException("Invalid answer, because the answer is not selectable");
        }
        return CorrectAnswer.contentEquals(answer.Answer());
    }
}
