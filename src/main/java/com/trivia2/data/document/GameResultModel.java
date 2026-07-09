package com.trivia2.data.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "results")
public class GameResultModel {
    @Field("incorrectly_answered")
    public int incorrectlyAnswered;
    @Field("invalid_answered")
    public int invalidlyAnswered;
    @Field("correctly_answered")
    public int correctlyAnswered;

    public GameResultModel(int incorrectlyAnswered, int invalidAnswered, int correctlyAnswered){
        this.invalidlyAnswered = invalidAnswered;
        this.correctlyAnswered = correctlyAnswered;
        this.incorrectlyAnswered = incorrectlyAnswered;
    }

    public int getInvalidlyAnswered() {
        return invalidlyAnswered;
    }

    public void setInvalidlyAnswered(int invalidlyAnswered) {
        this.invalidlyAnswered = invalidlyAnswered;
    }

    public int getCorrectlyAnswered() {
        return correctlyAnswered;
    }

    public void setCorrectlyAnswered(int correctlyAnswered) {
        this.correctlyAnswered = correctlyAnswered;
    }

    public int getIncorrectlyAnswered() {
        return incorrectlyAnswered;
    }

    public void setIncorrectlyAnswered(int incorrectlyAnswered) {
        this.incorrectlyAnswered = incorrectlyAnswered;
    }
}
