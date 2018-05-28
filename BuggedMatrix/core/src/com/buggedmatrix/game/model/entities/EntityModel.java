package com.buggedmatrix.game.model.entities;

public abstract class EntityModel {

    public enum ModelType {PLAYER, WALL, BULLET1, BULLET2, CHEST1, CHEST2, LEG1, LEG2, HEAD1, HEAD2, ARM1, ARM2};

    private float x;
    private float y;
    private float rotation;
    private boolean flaggedForRemoval;

    public EntityModel(float x, float y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setFlaggedForRemoval(boolean remove)
    {
        flaggedForRemoval = remove;
    }

    public boolean isFlaggedForRemoval()
    {
        return flaggedForRemoval;
    }

    public abstract ModelType getType();

}
