package com.trivia2.entities;

import java.util.UUID;

public class Game {
    private UUID GameId;

    public Game(){
        GameId = UUID.randomUUID();
    }

    public UUID getGameId() {
        return GameId;
    }
}
