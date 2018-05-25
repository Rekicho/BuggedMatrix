package com.buggedmatrix.game.model.entities;

public class BulletModel extends EntityModel {
    static final private int MAX_BOUNCES = 1;

    private final int playerID;
    private int bounces = 0;
    private boolean initial = true;

    public BulletModel(float x, float y, int rotation, int playerID)
    {
        super(x,y,rotation);
        this.playerID = playerID;
    }

    public ModelType getType() {
        return ModelType.BULLET;
    }

    public void bounce()
    {
        bounces++;

        if(bounces > MAX_BOUNCES)
            setFlaggedForRemoval(true);
    }

    public int getPlayerID() { return playerID; }

    public boolean isInitial()
    {
        if(!initial)
            return false;

        initial = false;
        return true;
    }
}
