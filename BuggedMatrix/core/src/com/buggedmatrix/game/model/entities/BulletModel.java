package com.buggedmatrix.game.model.entities;

/**
 * A model representing a single bullet.
 */
public class BulletModel extends EntityModel {

    /**
     * Constant of maximum bounces
     */
    static final public int MAX_BOUNCES = 1;

    /**
     * Bullet direction
     */
    private final float direction;

    /**
     * Bullets's player ID
     */
    private final int playerID;

    /**
     * Current bounces
     */
    private int bounces = 0;

    /**
     * Initial use
     */
    private boolean initial = true;

    /**
     * Type
     */
    private final ModelType type;

    /**
     * Constructs a bullet model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation The rotation of this bullet.
     */
    public BulletModel(float x, float y, int rotation, int playerID) {
        super(x,y,rotation);
        this.playerID = playerID;

        if(playerID == 1) {
            type = ModelType.BULLET1;
            this.direction = 1f;
        }
        else {
            type = ModelType.BULLET2;
            this.direction = -1f;
        }
    }

    @Override
    public ModelType getType() {
        return type;
    }

    /**
     * Bounce bullet
     */
    public void bounce() {
        bounces++;

        if(bounces > MAX_BOUNCES)
            setFlaggedForRemoval(true);
    }

    /**
     * Get player ID
     * @return player ID
     */
    public int getPlayerID() { return playerID; }

    /**
     * Get initial
     * @return initial
     */
    public boolean isInitial() {
        return initial;
    }

    /**
     * Set initial
     * @param initial initial
     */
    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    /**
     * Get direction
     * @return direction
     */
    public float getDirection() {
        return direction;
    }
}
