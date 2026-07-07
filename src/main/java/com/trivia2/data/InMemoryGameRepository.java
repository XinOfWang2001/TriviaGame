package com.trivia2.data;

import com.trivia2.entities.Game;
import com.trivia2.services.abstraction.IGameRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component("in-memory-games")
public class InMemoryGameRepository implements IGameRepository {
    // An in-memory database.
    public HashMap<UUID, Game> OngoingGames;

    public InMemoryGameRepository(){
        OngoingGames = new HashMap<>();
    }

    @Override
    public void CreateGame(Game game){
        this.OngoingGames.put(game.getGameId(), game);
    }

    @Override
    public Game GrabGame(UUID gameId){
        try {
            return OngoingGames.get(gameId);
        } catch (Exception ex){
            throw new IllegalStateException("No game present");
        }
    }
}
