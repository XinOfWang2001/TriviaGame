package com.trivia2.data;

import com.trivia2.entities.Game;

import java.util.HashMap;
import java.util.UUID;

public class GameRepository {
    // An in-memory database.
    public HashMap<UUID, Game> OngoingGames;

    public GameRepository(){
        OngoingGames = new HashMap<>();
    }

    public void CreateGame(Game game){
        this.OngoingGames.put(game.getGameId(), game);
    }

    public Game GrabGame(UUID gameId){
        try {
            return OngoingGames.get(gameId);
        } catch (Exception ex){
            throw new IllegalStateException("No game present");
        }

    }
}
