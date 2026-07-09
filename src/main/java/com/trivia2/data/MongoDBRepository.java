package com.trivia2.data;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.trivia2.data.document.GameModel;
import com.trivia2.entities.Game;
import com.trivia2.services.abstraction.IGameRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component("mongo-games")
public class MongoDBRepository implements IGameRepository {
    private MongoCollection<GameModel> games;

    public MongoDBRepository(MongoDBClient client){
        MongoClient mongoClient = client.mongoClient();
        MongoDatabase database = mongoClient.getDatabase("trivia");
        games = database.getCollection("questions_answers", GameModel.class);
    }

    @Override
    public void CreateGame(Game game) {
        GameModel model = new GameModel(new ArrayList<>(), game.getTrivia(), game.state, game.getGameId());
        games.insertOne(model);
    }

    @Override
    public Game GrabGame(UUID gameId) {
        GameModel haik = games.find(Filters.eq("_id", gameId)).first();
        assert haik != null;
        return new Game(haik._id, haik.gameState, haik.questions);
    }
}
