package com.trivia2.entities;

import java.util.List;
import java.util.UUID;

public record GameSession(UUID GameId, List<Question> Questions) {
}
