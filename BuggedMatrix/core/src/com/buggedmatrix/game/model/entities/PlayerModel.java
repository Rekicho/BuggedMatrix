package com.buggedmatrix.game.model.entities;

public class PlayerModel extends EntityModel {

    private final int playerID;

    private final MemberModel leftleg;
    private final MemberModel rightleg;
    private final MemberModel leftarm;
    private final MemberModel rightarm;
    private final MemberModel chest;
    private final MemberModel head;
    private final MemberModel gun;

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

    public MemberModel getGun() {
        return gun;
    }
}
