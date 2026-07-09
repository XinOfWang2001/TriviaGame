package com.trivia2.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Game {
    private final UUID gameId;
    public GameState state;
    private final HashMap<String, Trivia> questions;

    public Game(){
        questions = new HashMap<>();
        state = GameState.ACTIVE;
        gameId = UUID.randomUUID();
    }

    public Game(UUID id, GameState state, List<Trivia> trivia){
        gameId = id;
        this.state = state;
        this.questions = new HashMap<>();
        setQuestions(trivia);
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setQuestions(List<Trivia> trivia) {
        for (Trivia trivial:trivia) {
            this.questions.put(trivial.question, trivial);
        }
    }

    public List<Trivia> getTrivia(){
        return this.questions.values().stream().toList();
    }

    public List<Question> getQuestions(){
        return new ArrayList<>(this.questions.values().stream().map(Trivia::getQuestion).toList());
    }

    public GameSession GetGameSession(){
        return new GameSession(gameId, getQuestions());
    }


    public void ChangeToSolved(){
        state = GameState.SOLVED;
    }

    // Submit answers
    public GameResult SubmitAnswers(List<Answer> answers){
        GameResult gameResult = new GameResult();
        // Start validating.
        for (Answer answer: answers) {
            try {
                Trivia question = this.questions.get(answer.question());
                if(question.ValidateAnswer(answer)){
                    gameResult.IncreaseCorrect();
                    continue;
                }
                gameResult.IncreaseIncorrect();
            } catch (Exception value){
                gameResult.IncreaseInvalid();
            }
        }
        ChangeToSolved();
        return gameResult;
    }
}
