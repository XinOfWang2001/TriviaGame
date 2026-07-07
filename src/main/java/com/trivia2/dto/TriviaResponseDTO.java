package com.trivia2.dto;

import java.util.List;

public record TriviaResponseDTO(int response_code, List<TriviaDTO> results) {
}
