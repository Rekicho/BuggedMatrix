package com.buggedmatrix.game.model;

import com.buggedmatrix.game.controller.entities.PlayerBody;
import com.buggedmatrix.game.model.entities.BulletModel;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.PlayerModel;
import com.buggedmatrix.game.model.entities.WallModel;

import static com.buggedmatrix.game.controller.GameController.MATRIX_HEIGTH;
import static com.buggedmatrix.game.controller.GameController.MATRIX_WIDTH;

public class GameModel {

    //singleton
    private static GameModel instance;

    private PlayerModel playerOne;
    private PlayerModel playerTwo;

    private BulletModel playerOneBullet;
    private BulletModel playerTwoBullet;

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
        playerOne = new PlayerModel(25,25, -45, 1);

        playerTwo = new PlayerModel(65,25, -45, 2);

        leftWall = new WallModel(0, -(MATRIX_HEIGTH + 3)/2, 0);

        rightWall = new WallModel(MATRIX_WIDTH, -(MATRIX_HEIGTH + 3)/2, 0);

        floorWall = new WallModel(MATRIX_WIDTH/2, 0 , 0);

        ceelingWall = new WallModel(MATRIX_WIDTH/2, MATRIX_HEIGTH, 0);
    }

    public PlayerModel getPlayerOne() {
        return playerOne;
    }

    public PlayerModel getPlayerTwo() { return playerTwo; }

    public BulletModel getPlayerOneBullet()
    {
        if(playerOneBullet == null)
            playerOneBullet = new BulletModel(playerOne.getLeftarm().getX(), playerOne.getLeftarm().getY(), 0, 1);

        return playerOneBullet;
    }

    public BulletModel getBulletOne()
    {
        return playerOneBullet;
    }

    public BulletModel getPlayerTwoBullet()
    {
        if(playerTwoBullet == null)
            playerTwoBullet = new BulletModel(playerTwo.getLeftarm().getX(), playerTwo.getLeftarm().getY(), 0, 2);

        return playerTwoBullet;
    }

    public BulletModel getBulletTwo()
    {
        return playerTwoBullet;
    }

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

    public void remove(EntityModel model)
    {
        if (model instanceof BulletModel)
        {
            if(((BulletModel) model).getPlayerID() == 1)
                playerOneBullet = null;

            else if(((BulletModel) model).getPlayerID() == 2)
                playerTwoBullet = null;
        }
    }
}
