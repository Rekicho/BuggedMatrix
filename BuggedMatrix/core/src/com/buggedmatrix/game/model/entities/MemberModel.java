package com.buggedmatrix.game.model.entities;


/**
 * A model representing a single member.
 */
public class MemberModel extends EntityModel {

    /**
     * Member's player ID
     */
    private final int playerID;

    /**
     * Type
     */
    private ModelType type;

    /**
     * Constructs a member model belonging to a player model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation Member rotation
     * @param playerID Player's ID
     * @param type Type of member
     */
    public MemberModel(float x, float y, int rotation, int playerID, ModelType type) {
        super(x, y, rotation);
        this.playerID = playerID;
        this.type = type;
    }

    @Override
    public ModelType getType() {
        return type;
    }

    /**
     * Get player ID
     * @return player ID
     */
    public int getPlayerID() { return playerID; }
}