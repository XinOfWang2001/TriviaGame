package com.trivia2.entities;

import java.util.List;

public record Question(String question, List<String> choices) {
}
