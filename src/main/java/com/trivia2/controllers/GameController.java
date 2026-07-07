package com.trivia2.controllers;

import com.trivia2.dto.AnswerDTO;
import com.trivia2.entities.GameResult;
import com.trivia2.entities.GameSession;
import com.trivia2.services.implementation.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String Health(){
        return "Positive";
    }

    @GetMapping("/game")
    public ResponseEntity<GameSession> GetResult(@RequestParam(defaultValue="5") int amount){
        try {
            return ResponseEntity.ok(gameService.CreateGame(amount));
        } catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/game/solve")
    public ResponseEntity<GameResult> SolveQuestions(@RequestBody AnswerDTO body){
        try {
            System.out.println("Triggered this endpoint.");
            return ResponseEntity.ok(gameService.PostAnswers(body.gameId(), body.answers()));
        } catch (Exception exception){
            // Return error message
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
