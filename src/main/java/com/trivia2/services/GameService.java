package com.trivia2.services;

import com.trivia2.data.GameRepository;
import com.trivia2.data.TriviaAPI;
import com.trivia2.entities.Answer;
import com.trivia2.entities.Game;
import com.trivia2.entities.GameResult;
import com.trivia2.entities.GameSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final TriviaAPI triviaAPI;

    public GameService(GameRepository gameRepository, TriviaAPI triviaAPI){
        this.gameRepository = gameRepository;
        this.triviaAPI = triviaAPI;
    }

    public GameSession CreateGame(){
        Game newGame = new Game();
        var trivia = triviaAPI.GetQuestions();
        newGame.setQuestions(trivia);
        // Store game temporarily.
        gameRepository.CreateGame(newGame);
        System.out.println(gameRepository.OngoingGames.size());
        return newGame.GetGameSession();
    }

    public GameResult PostAnswers(UUID gameId, List<Answer> answers){
        System.out.println(gameId);
        Game ongoingGame = gameRepository.GrabGame(gameId);
        // Throw error if answers are invalid

        // Change status in game repository.
        return ongoingGame.SubmitAnswers(answers);
    }
}
