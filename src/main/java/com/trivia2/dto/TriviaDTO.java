package com.trivia2.dto;

import java.util.List;

public record TriviaDTO(String type, String difficulty, String category, String question, String correct_answer, List<String> incorrect_answers) {
}
