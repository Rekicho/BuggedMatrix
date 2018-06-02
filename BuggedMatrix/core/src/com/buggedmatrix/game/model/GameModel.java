package com.buggedmatrix.game.model;

import com.buggedmatrix.game.model.entities.BulletModel;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.PlayerModel;
import com.buggedmatrix.game.model.entities.WallModel;

import static com.buggedmatrix.game.controller.GameController.MATRIX_HEIGHT;
import static com.buggedmatrix.game.controller.GameController.MATRIX_WIDTH;

/**
 * A model representing a game.
 */
public class GameModel {

    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    /**
     * Player one score
     */
    private static int playerOneScore = 0;

    /**
     * Player two score
     */
    private static int playerTwoScore = 0;

    /**
     * Reset control variable
     */
    boolean reset = false;

    /**
     * Player one
     */
    private PlayerModel playerOne;

    /**
     * Player two
     */
    private PlayerModel playerTwo;

    /**
     * Player one bullet
     */
    private BulletModel playerOneBullet;

    /**
     * Player two bullet
     */
    private BulletModel playerTwoBullet;

    /**
     * Left wall
     */
    private WallModel leftWall;

    /**
     * Right wall
     */
    private WallModel rightWall;

    /**
     * Floor
     */
    private WallModel floorWall;

    /**
     * Ceiling
     */
    private WallModel ceilingWall;

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }

        return instance;
    }

    /**
     * Constructs a game with two players and four walls
     */
    private GameModel()
    {
        playerOne = new PlayerModel(35,25, -45, 1);
        playerTwo = new PlayerModel(75,25, -45, 2);

        leftWall = new WallModel(0, -(MATRIX_HEIGHT + 3)/2, 0);
        rightWall = new WallModel(MATRIX_WIDTH, -(MATRIX_HEIGHT + 3)/2, 0);
        floorWall = new WallModel(MATRIX_WIDTH/2, 0 , 0);
        ceilingWall = new WallModel(MATRIX_WIDTH/2, MATRIX_HEIGHT, 0);
    }

    /**
     * Get player one
     * @return player one model
     */
    public PlayerModel getPlayerOne() {
        return playerOne;
    }

    /**
     * Get player two
     * @return player two model
     */
    public PlayerModel getPlayerTwo() { return playerTwo; }

    /**
     * Get player one bullet
     * @return player one bullet model
     */
    public BulletModel getPlayerOneBullet()
    {
        if(playerOneBullet == null)
            playerOneBullet = new BulletModel(playerOne.getGun().getX(), playerOne.getGun().getY(), (int) playerOne.getGun().getRotation(), 1);

        return playerOneBullet;
    }

    /**
     * Get player one bullet
     * @return player one bullet model
     */
    public BulletModel getBulletOne()
    {
        return playerOneBullet;
    }

    /**
     * Get player two bullet
     * @return player two bullet model
     */
    public BulletModel getPlayerTwoBullet()
    {
        if(playerTwoBullet == null)
            playerTwoBullet = new BulletModel(playerTwo.getGun().getX(), playerTwo.getGun().getY(), (int) playerTwo.getGun().getRotation(), 2);

        return playerTwoBullet;
    }

    /**
     * Get player two bullet
     * @return player two bullet model
     */
    public BulletModel getBulletTwo()
    {
        return playerTwoBullet;
    }

    /**
     * Get wall
     * @return left wall
     */
    public WallModel getLeftWall() {
        return leftWall;
    }

    /**
     * Get ceiling
     * @return ceiling
     */
    public WallModel getCeilingWall() {
        return ceilingWall;
    }

    /**
     * Get floor
     * @return floor
     */
    public WallModel getFloorWall() {
        return floorWall;
    }

    /**
     * Get wall
     * @return right wall
     */
    public WallModel getRightWall() {
        return rightWall;
    }

    public void update(float delta)
    {
        playerOne.setShootTime(playerOne.getShootTime() + delta);
        playerTwo.setShootTime(playerTwo.getShootTime() + delta);
    }

    /**
     * Removes a model from this game
     * @param model the model to be removed
     */
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

    /**
     * Updates player's score at end of the round
     * @param player player who won
     */
    public void roundWin(int player)
    {
        if (reset)
            return;

        if(player == 1)
            playerOneScore++;

        else if(player == 2)
            playerTwoScore++;

        reset = true;
    }

    /**
     * Check if any player has 3 points
     * @return 0 if isn't over else winner
     */
    public int checkGameOver()
    {
        if(playerOneScore >= 3)
            return 1;

        if(playerTwoScore >= 3)
            return 2;

        return 0;
    }

    /**
     * Needs reset?
     * @return reset
     */
    public boolean needsReset()
    {
        return reset;
    }

    /**
     * Get player one score
     * @return player one score
     */
    public static int getPlayerOneScore() {
        return playerOneScore;
    }

    /**
     * Get player two score
     * @return player two score
     */
    public static int getPlayerTwoScore() {
        return playerTwoScore;
    }

    /**
     * Resets instance and players scores
     */
    public static void reset()
    {
        instance = null;
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    /**
     * Resets instance
     */
    public static void softReset()
    {
        instance = null;
    }
}
