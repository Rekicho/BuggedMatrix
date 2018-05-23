package com.buggedmatrix.game.model;

import com.buggedmatrix.game.model.entities.PlayerModel;
import com.buggedmatrix.game.model.entities.WallModel;

import static com.buggedmatrix.game.controller.GameController.MATRIX_HEIGTH;
import static com.buggedmatrix.game.controller.GameController.MATRIX_WIDTH;

public class GameModel {

    //singleton
    private static GameModel instance;

    private PlayerModel playerOne;
    private PlayerModel playerTwo;

    private WallModel leftWall;

    private WallModel rightWall;

    private WallModel floorWall;

    private WallModel ceelingWall;

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }

        return instance;
    }

    private GameModel()
    {
        playerOne = new PlayerModel(25,25, 0);

        playerTwo = new PlayerModel(65,25, 0);

        leftWall = new WallModel(0, -(MATRIX_HEIGTH + 3)/2, 0);

        rightWall = new WallModel(MATRIX_WIDTH, -(MATRIX_HEIGTH + 3)/2, 0);

        floorWall = new WallModel(MATRIX_WIDTH/2, 0 , 0);

        ceelingWall = new WallModel(MATRIX_WIDTH/2, MATRIX_HEIGTH, 0);
    }

    public PlayerModel getPlayerOne() {
        return playerOne;
    }

    public PlayerModel getPlayerTwo() { return playerTwo; }

    public WallModel getLeftWall() {
        return leftWall;
    }

    public WallModel getCeelingWall() {
        return ceelingWall;
    }

    public WallModel getFloorWall() {
        return floorWall;
    }

    public WallModel getRightWall() {
        return rightWall;
    }

    public void update(float delta) {}
}
