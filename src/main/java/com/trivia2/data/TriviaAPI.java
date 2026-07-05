package com.trivia2.data;

import com.trivia2.entities.Game;
import com.trivia2.entities.Trivia;
import com.trivia2.services.Parser;

import java.util.ArrayList;
import java.util.List;

public class TriviaAPI {
    public final String apiUrl = "https://localhost.com";
    private final Parser parser;

    public TriviaAPI(Parser parser){
        this.parser = parser;
    }

    public List<Trivia> GetQuestions(){
        // HTTP Request

        // Parse questions to format

        return new ArrayList<>();
    }
}
