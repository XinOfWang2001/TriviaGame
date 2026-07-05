package com.trivia2.controllers;

import com.trivia2.entities.GameSession;
import com.trivia2.services.GameService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public GameSession  GetResult(){
        return gameService.CreateGame();
    }

    @PutMapping("/game/solve/{id}")
    public String SolveQuestions(UUID id){
        return "Solved";
    }
}
