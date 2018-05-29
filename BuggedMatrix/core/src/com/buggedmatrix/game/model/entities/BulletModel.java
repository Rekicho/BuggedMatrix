package com.buggedmatrix.game.model.entities;

public class BulletModel extends EntityModel {
    static final private int MAX_BOUNCES = 1;
    private final float direction;

    private final int playerID;
    private int bounces = 0;
    private boolean initial = true;
    private final ModelType type;

    public BulletModel(float x, float y, int rotation, int playerID)
    {
        super(x,y,rotation);
        this.playerID = playerID;

        if(playerID == 1) {
            type = ModelType.BULLET1;
            this.direction = 1f;
        }
        else {
            type = ModelType.BULLET2;
            this.direction = -1f;
        }
    }

    public ModelType getType() {
        return type;
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

    public float getDirection() {
        return direction;
    }
}
