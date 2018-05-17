package com.buggedmatrix.game.model.entities;

public class WallModel extends EntityModel {

    public WallModel(float x, float y, int rotation) {
        super(x, y, rotation);
    }

    @Override
    public ModelType getType() {
        return ModelType.WALL;
    }

}
