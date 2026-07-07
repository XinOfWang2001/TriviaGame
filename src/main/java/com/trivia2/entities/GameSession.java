package com.trivia2.entities;

import java.util.List;
import java.util.UUID;

public record GameSession(UUID gameId, List<Question> questions) {
}
