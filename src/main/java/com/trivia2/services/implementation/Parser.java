package com.trivia2.services.implementation;

import com.trivia2.dto.TriviaDTO;
import com.trivia2.entities.Trivia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Parser {
    public Trivia Parse(TriviaDTO trivia){
        List<String> AvailableAnswers = new ArrayList<>(trivia.incorrect_answers());
        AvailableAnswers.add(trivia.correct_answer());
        return new Trivia(trivia.question(), trivia.correct_answer(), AvailableAnswers);
    }
}
