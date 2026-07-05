package com.trivia2.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GameController {

    @GetMapping("/game")
    public String  GetResult(){
        return "Hello sir?";
    }

    @PutMapping("/game/solve/{id}")
    public String SolveQuestions(UUID id){
        return "Solved";
    }
}
