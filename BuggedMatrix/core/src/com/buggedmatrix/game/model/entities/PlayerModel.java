package com.buggedmatrix.game.model.entities;

/**
 * A model representing a single player.
 */
public class PlayerModel extends EntityModel {

    /**
     * Player's ID
     */
    private final int playerID;

    /**
     * Time since last shot
     */
    private float shootTime = 0;

    /**
     * Player's left leg
     */
    private final MemberModel leftleg;

    /**
     * Player's right leg
     */
    private final MemberModel rightleg;

    /**
     * Player's left arm
     */
    private final MemberModel leftarm;

    /**
     * Player's right arm
     */
    private final MemberModel rightarm;

    /**
     * Player's chest
     */
    private final MemberModel chest;

    /**
     * Player's head
     */
    private final MemberModel head;

    /**
     * Player's gun
     */
    private final MemberModel gun;

    /**
     * Constructs a player model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation Member rotation
     * @param playerID Player's ID
     */
    public PlayerModel(float x, float y, int rotation, int playerID)
    {
        super(x, y, rotation);
        this.playerID = playerID;


        if(playerID == 1)
        {
            chest = new MemberModel(0, 0, 0, playerID, ModelType.CHEST1);
            leftleg = new MemberModel(0, 0, 0, playerID, ModelType.LEG1);
            rightleg = new MemberModel(0, 0, 0, playerID, ModelType.LEG1);
            leftarm = new MemberModel(0, 0, 0, playerID, ModelType.ARM1);
            rightarm = new MemberModel(0, 0, 0, playerID, ModelType.ARM1);
            head = new MemberModel(0, 0, 0, playerID, ModelType.HEAD1);
            gun = new MemberModel(0, 0, 0, playerID, ModelType.GUN);
        }

        else
        {
            chest = new MemberModel(0, 0, 0, playerID, ModelType.CHEST2);
            leftleg = new MemberModel(0, 0, 0, playerID, ModelType.LEG2);
            rightleg = new MemberModel(0, 0, 0, playerID, ModelType.LEG2);
            leftarm = new MemberModel(0, 0, 0, playerID, ModelType.ARM2);
            rightarm = new MemberModel(0, 0, 0, playerID, ModelType.ARM2);
            head = new MemberModel(0, 0, 0, playerID, ModelType.HEAD2);
            gun = new MemberModel(0, 0, 0, playerID, ModelType.GUN);
        }
    }

    /**
     * Get type
     * @return type
     */
    public ModelType getType() {
        return ModelType.PLAYER;
    }

    /**
     * Get player ID
     * @return player ID
     */
    public int getPlayerID() { return playerID; }

    /**
     * Get shoot time
     * @return shoot time
     */
    public float getShootTime() { return shootTime; }

    /**
     * Set shoot time
     * @param shootTime time since last shot
     */
    public void setShootTime(float shootTime) {
        this.shootTime = shootTime;
    }

    /**
     * Get chest
     * @return chest
     */
    public MemberModel getChest() {
        return chest;
    }

    /**
     * Get left leg
     * @return left leg
     */
    public MemberModel getLeftleg() {
        return leftleg;
    }

    /**
     * Get right leg
     * @return right leg
     */
    public MemberModel getRightleg() {
        return rightleg;
    }

    /**
     * Get left arm
     * @return left arm
     */
    public MemberModel getLeftarm() {
        return leftarm;
    }

    /**
     * Get right arm
     * @return right arm
     */
    public MemberModel getRightarm() {
        return rightarm;
    }

    /**
     * Get head
     * @return head
     */
    public MemberModel getHead() {
        return head;
    }

    /**
     * Get gun
     * @return gun
     */
    public MemberModel getGun() {
        return gun;
    }
}
