package com.trivia2.dto;

import com.trivia2.entities.Answer;

import java.util.List;
import java.util.UUID;

public record AnswerDTO(UUID gameId, List<Answer> answers) {
}
