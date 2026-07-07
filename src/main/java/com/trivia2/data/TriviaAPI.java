package com.trivia2.data;

import com.trivia2.dto.TriviaResponseDTO;
import com.trivia2.entities.Trivia;
import com.trivia2.services.implementation.Parser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriviaAPI {
    public final String apiUrl = "https://opentdb.com/";
    private final RestClient restClient;
    private final Parser parser;

    public TriviaAPI(Parser parser){
        this.parser = parser;
        this.restClient = RestClient.builder()
                .baseUrl(apiUrl)
                .build();
    }

    public List<Trivia> GetQuestions(int amount){
        // HTTP Request
        TriviaResponseDTO answer = restClient
                .get()
                .uri("api.php?amount={value}", amount)
                .retrieve()
                .body(TriviaResponseDTO.class);
        // Parse questions to format
        assert answer != null;
        return answer.results().stream().map(parser::Parse).collect(Collectors.toList());
    }
}
