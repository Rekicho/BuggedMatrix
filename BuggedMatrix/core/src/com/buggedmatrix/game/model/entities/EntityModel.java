package com.buggedmatrix.game.model.entities;

public abstract class EntityModel {

    public enum ModelType {PLAYER};

    private float x;
    private float y;
    private float size_x;
    private float size_y;

    public EntityModel(float x, float y) {
        this.x = x;
        this.y = y;
        this.size_x = size_x;
        this.size_y = size_y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract ModelType getType();

}
