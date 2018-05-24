package com.buggedmatrix.game.model.entities;

public abstract class EntityModel {

    public enum ModelType {PLAYER, WALL, CHEST, LEG};

    private float x;
    private float y;
    private float rotation;

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

    public abstract ModelType getType();

}
