package com.buggedmatrix.game.model;

import com.buggedmatrix.game.model.entities.PlayerModel;

public class GameModel {

    //singleton
    private static GameModel instance;

    private PlayerModel playerOne;

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }

        return instance;
    }

    private GameModel() {
        playerOne = new PlayerModel(10,10);
    }

    public PlayerModel getPlayerOne() {
        return playerOne;
    }

    public void update(float delta) {

    }
}
