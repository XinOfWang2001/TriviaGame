package com.trivia2.services.implementation;

import com.trivia2.data.TriviaAPI;
import com.trivia2.entities.Answer;
import com.trivia2.entities.Game;
import com.trivia2.entities.GameResult;
import com.trivia2.entities.GameSession;
import com.trivia2.services.abstraction.IGameRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final IGameRepository gameRepository;
    private final TriviaAPI triviaAPI;

    public GameService(@Qualifier("in-memory-games") IGameRepository gameRepository, TriviaAPI triviaAPI){
        this.gameRepository = gameRepository;
        this.triviaAPI = triviaAPI;
    }

    public GameSession CreateGame(int amount){
        Game newGame = new Game();
        var trivia = triviaAPI.GetQuestions(amount);
        newGame.setQuestions(trivia);
        // Store game temporarily.
        gameRepository.CreateGame(newGame);
        return newGame.GetGameSession();
    }

    public GameResult PostAnswers(UUID gameId, List<Answer> answers){
        System.out.println(gameId);
        Game ongoingGame = gameRepository.GrabGame(gameId);
        // Change status in game repository.
        return ongoingGame.SubmitAnswers(answers);
    }
}
