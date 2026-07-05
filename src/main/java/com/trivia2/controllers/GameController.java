package com.trivia2.controllers;

import com.trivia2.dto.AnswerDTO;
import com.trivia2.entities.GameResult;
import com.trivia2.entities.GameSession;
import com.trivia2.services.GameService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOError;
import java.util.UUID;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public GameSession  GetResult(){
        System.out.println("Create game");
        return gameService.CreateGame();
    }

    @PutMapping("/game/solve")
    public GameResult SolveQuestions(@RequestBody AnswerDTO body){
        try {
            System.out.println("Triggered this endpoint.");
            return gameService.PostAnswers(body.gameId(), body.answers());
        } catch (Exception exception){
            // Return error message
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
