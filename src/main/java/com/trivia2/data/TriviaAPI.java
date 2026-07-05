package com.trivia2.data;

import com.trivia2.dto.QuestionAnswer;
import com.trivia2.dto.TriviaResponse;
import com.trivia2.entities.Game;
import com.trivia2.entities.Trivia;
import com.trivia2.services.Parser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriviaAPI {
    public final String apiUrl = "https://opentdb.com/";
    private RestClient restClient;
    private final Parser parser;

    public TriviaAPI(Parser parser){
        this.parser = parser;
        this.restClient = RestClient.builder()
                .baseUrl(apiUrl)
                .build();
    }

    public List<Trivia> GetQuestions(){
        try {
            // HTTP Request
            TriviaResponse answer = restClient
                    .get()
                    .uri("api.php?amount={value}", 1)
                    .retrieve()
                    .body(TriviaResponse.class);
            // Parse questions to format
            assert answer != null;
            return answer.results().stream().map(parser::Parse).collect(Collectors.toList());
        } catch (Exception exc){
             throw exc;
        }
    }
}
