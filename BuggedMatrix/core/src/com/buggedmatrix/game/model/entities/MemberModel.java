package com.buggedmatrix.game.model.entities;

public class MemberModel extends EntityModel {

    private final int playerID;
    private ModelType type;

    public MemberModel(float x, float y, int rotation, int playerID, ModelType type) {

        super(x, y, rotation);
        this.playerID = playerID;
        this.type = type;
    }

    @Override
    public ModelType getType() {
        return type;
    }

    public int getPlayerID() { return playerID; }
}