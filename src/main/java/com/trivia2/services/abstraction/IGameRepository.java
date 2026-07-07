package com.trivia2.services.abstraction;

import com.trivia2.entities.Game;

import java.util.UUID;

public interface IGameRepository {
    void CreateGame(Game game);

    Game GrabGame(UUID gameId);
}
