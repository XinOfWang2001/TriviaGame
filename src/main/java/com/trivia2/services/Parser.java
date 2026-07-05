package com.trivia2.services;

import com.trivia2.dto.QuestionAnswer;
import com.trivia2.entities.Trivia;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public Trivia Parse(QuestionAnswer trivia){
        List<String> AvailableAnswers = new ArrayList<>(trivia.incorrect_answers());
        AvailableAnswers.add(trivia.correct_answer());
        return new Trivia(trivia.question(), trivia.correct_answer(), AvailableAnswers);
    }
}
