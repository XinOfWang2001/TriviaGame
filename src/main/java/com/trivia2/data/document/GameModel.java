package com.trivia2.data.document;

import com.trivia2.entities.Answer;
import com.trivia2.entities.GameState;
import com.trivia2.entities.Trivia;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

public class GameModel {

    public UUID _id;
    public GameState gameState;
    public List<Trivia> questions;
    public List<Answer> answers;

    public GameModel(List<Answer> answers, List<Trivia> questions, GameState gameState, UUID gameId) {
        this.answers = answers;
        this.questions = questions;
        this.gameState = gameState;
        this._id = gameId;
    }

    public UUID getGameId() {
        return _id;
    }

    public void setGameId(UUID gameId) {
        this._id = gameId;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Trivia> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Trivia> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}