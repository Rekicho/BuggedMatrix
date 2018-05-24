package com.buggedmatrix.game.model.entities;

public class MemberModel extends EntityModel {

    public MemberModel(float x, float y, int rotation) {
        super(x, y, rotation);
    }

    @Override
    public ModelType getType() {
        return ModelType.CHEST;
    }

}
