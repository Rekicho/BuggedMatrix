package com.buggedmatrix.game.model.entities;

/**
 * A model representing a single player.
 */
public class WallModel extends EntityModel {

    /**
     * Constructs a wall model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation Member rotation
     */
    public WallModel(float x, float y, int rotation) {
        super(x, y, rotation);
    }

    @Override
    public ModelType getType() {
        return ModelType.WALL;
    }

}
