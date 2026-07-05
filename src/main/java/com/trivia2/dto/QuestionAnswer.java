package com.trivia2.dto;

import java.util.List;

public record QuestionAnswer(String type, String difficulty, String category, String question, String correct_answer, List<String> incorrect_answers) {
}
