package com.buggedmatrix.game.model;

import com.buggedmatrix.game.controller.entities.PlayerBody;
import com.buggedmatrix.game.model.entities.BulletModel;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.PlayerModel;
import com.buggedmatrix.game.model.entities.WallModel;

import static com.buggedmatrix.game.controller.GameController.MATRIX_HEIGTH;
import static com.buggedmatrix.game.controller.GameController.MATRIX_WIDTH;

public class GameModel {
    private static GameModel instance;
    private static int playerOneLifes = 3;
    private static int playerTwoLifes = 3;
    boolean reset = false;

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
        playerOne = new PlayerModel(35,25, -45, 1);

        playerTwo = new PlayerModel(75,25, -45, 2);

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
            playerOneBullet = new BulletModel(playerOne.getGun().getX(), playerOne.getGun().getY(), 0, 1);

        return playerOneBullet;
    }

    public BulletModel getBulletOne()
    {
        return playerOneBullet;
    }

    public BulletModel getPlayerTwoBullet()
    {
        if(playerTwoBullet == null)
            playerTwoBullet = new BulletModel(playerTwo.getGun().getX(), playerTwo.getGun().getY(), 0, 2);

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

    public void takeLife(int player)
    {
        if(player == 1)
            playerOneLifes--;

        else if(player == 2)
            playerTwoLifes--;

        reset = true;
    }

    public int checkGameOver()
    {
        if(playerOneLifes == 0)
            return 2;

        if(playerTwoLifes == 0)
            return 1;

        return 0;
    }

    public boolean needsReset()
    {
        return reset;
    }

    public static int getPlayerOneLifes() {
        return playerOneLifes;
    }

    public static int getPlayerTwoLifes() {
        return playerTwoLifes;
    }

    public static void reset()
    {
        instance = null;
        playerOneLifes = 3;
        playerTwoLifes = 3;
    }

    public static void softReset()
    {
        instance = null;
    }
}
