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

    public UUID getGameId() {
        return gameId;
    }

    public void setQuestions(List<Trivia> trivias) {
        for (Trivia trivia:trivias) {
            this.questions.put(trivia.question, trivia);
        }
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
                boolean result = question.ValidateAnswer(answer);
                if(result){
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
