package com.buggedmatrix.game.model.entities;

public class PlayerModel extends EntityModel {

    private final int playerID;

    private final MemberModel leftleg;
    private final MemberModel rightleg;
    private final MemberModel leftarm;
    private final MemberModel rightarm;
    private final MemberModel chest;
    private final MemberModel head;

    public PlayerModel(float x, float y, int rotation, int playerID)
    {
        super(x, y, rotation);
        this.playerID = playerID;

        chest = new MemberModel(0, 0, 0, playerID, ModelType.CHEST);
        leftleg = new MemberModel(0, 0, 0, playerID, ModelType.LEG);
        rightleg = new MemberModel(0, 0, 0, playerID, ModelType.LEG);
        leftarm = new MemberModel(0, 0, 0, playerID, ModelType.ARM);
        rightarm = new MemberModel(0, 0, 0, playerID, ModelType.ARM);
        head = new MemberModel(0, 0, 0, playerID, ModelType.HEAD);
    }

    public ModelType getType() {
        return ModelType.PLAYER;
    }

    public int getPlayerID() { return playerID; }

    public MemberModel getChest() {
        return chest;
    }

    public MemberModel getLeftleg() {
        return leftleg;
    }

    public MemberModel getRightleg() {
        return rightleg;
    }

    public MemberModel getLeftarm() {
        return leftarm;
    }

    public MemberModel getRightarm() {
        return rightarm;
    }

    public MemberModel getHead() {
        return head;
    }
}
