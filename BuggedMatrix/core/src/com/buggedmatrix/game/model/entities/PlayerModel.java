package com.buggedmatrix.game.model.entities;

public class PlayerModel extends EntityModel {

    public PlayerModel(float x, float y, int rotation) {
        super(x, y, rotation);
    }

    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }
}
