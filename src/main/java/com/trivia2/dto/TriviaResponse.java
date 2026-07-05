package com.trivia2.dto;

import java.util.List;

public record TriviaResponse(int response_code, List<QuestionAnswer> results) {
}
