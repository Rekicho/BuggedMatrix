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
    private static int playerOneScore = 0;
    private static int playerTwoScore = 0;
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

    public void update(float delta)
    {
        playerOne.setShootTime(playerOne.getShootTime() + delta);
        playerTwo.setShootTime(playerOne.getShootTime() + delta);
    }

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

    public void roundWin(int player)
    {
        if(player == 1)
            playerOneScore++;

        else if(player == 2)
            playerTwoScore++;

        reset = true;
    }

    public int checkGameOver()
    {
        if(playerOneScore >= 3)
            return 1;

        if(playerTwoScore >= 3)
            return 2;

        return 0;
    }

    public boolean needsReset()
    {
        return reset;
    }

    public static int getPlayerOneScore() {
        return playerOneScore;
    }

    public static int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public static void reset()
    {
        instance = null;
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    public static void softReset()
    {
        instance = null;
    }
}
