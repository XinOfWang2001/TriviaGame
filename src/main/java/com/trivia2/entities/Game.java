package com.trivia2.entities;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Game {
    private final UUID gameId;
    public GameState State;
    private final HashMap<String, Trivia> questions;

    public Game(){
        questions = new HashMap<>();
        State = GameState.ACTIVE;
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

    public Trivia getQuestion(String question){
        return questions.get(question);
    }

    public void ChangeToSolved(){
        State = GameState.SOLVED;
    }

    // Submit answers
    public GameResult SubmitAnswers(List<Answer> answers){
        GameResult gameResult = new GameResult();
        // Start validating.
        for (Answer answer: answers) {
            try {
                Trivia question = this.questions.get(answer.Question());
                boolean result = question.ValidateAnswer(answer);
                if(result){
                    gameResult.IncreaseCorrect();
                    continue;
                }
                gameResult.IncreaseIncorrect();
            } catch (Exception value){
                gameResult.IncreaseInvalide();
            }
        }
        ChangeToSolved();
        return gameResult;
    }
}
