package com.buggedmatrix.game.model.entities;

public class PlayerModel extends EntityModel {

    final private MemberModel leftleg;
    final private MemberModel rightleg;
    final private MemberModel leftarm;
    final private MemberModel rightarm;
    final private MemberModel chest;
    final private MemberModel head;

    public PlayerModel(float x, float y, int rotation) {
        super(x, y, rotation);

        chest = new MemberModel(0, 0, 0);
        leftleg = new MemberModel(0, 0, 0);
        rightleg = new MemberModel(0, 0, 0);
        leftarm = new MemberModel(0, 0, 0);
        rightarm = new MemberModel(0, 0, 0);
        head = new MemberModel(0, 0, 0);

    }

    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }

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
