package com.buggedmatrix.game.model.entities;

public class MemberModel extends EntityModel {

    private final int playerID;

    public MemberModel(float x, float y, int rotation, int playerID) {

        super(x, y, rotation);
        this.playerID = playerID;
    }

    @Override
    public ModelType getType() {
        return ModelType.CHEST;
    }

    public int getPlayerID() { return playerID; }
}