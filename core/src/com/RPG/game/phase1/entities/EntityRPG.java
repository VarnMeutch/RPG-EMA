package com.RPG.game.phase1.entities;

import com.RPG.game.common.Entity;
import com.badlogic.gdx.assets.AssetManager;

import java.util.ArrayList;

public abstract class EntityRPG extends Entity {

    private int gridX;
    private int gridY;

    // Size of a square of the map
    private int gridSize;

    private boolean moving;
    private float movingDuration;
    private int movingDirection;

    private long frameStartMoving;

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    public EntityRPG(ArrayList<Entity> entitiesList, AssetManager assetManager) {
        super(entitiesList, assetManager);
        moving = false;
    }

    // --------------------------- Getters & Setters -------------------------------------------------------------------

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
        setX(gridX * gridSize);
        moving = false;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
        setY(gridY * gridSize);
        moving = false;
    }

    public void setGridPosition(int gridX, int gridY){
        setGridX(gridX);
        setGridY(gridY);
    }

    // --------------------------- Methods -------------------------------------------------------------------

    /**
     * @param direction UP, RIGHT, LEFT ou DOWN
     * @param duration # de frame pour faire le mouvement
     */
    public void moveTo(int direction, float duration){
        this.movingDirection = direction;
        this.movingDuration = duration;

        frameStartMoving = m_frameCount;
    }

    public void updatePosition()
    {
        if(m_frameCount > frameStartMoving + movingDuration)
        {
            moving = false;
        }
        if(moving)
        {
            if(movingDirection == RIGHT)
            {
                setX(gridX*gridSize + (m_frameCount-frameStartMoving)*(gridSize/movingDuration));
            }
            if(movingDirection == LEFT)
            {
                setX(gridX*gridSize - (m_frameCount-frameStartMoving)*(gridSize/movingDuration));
            }
            if(movingDirection == RIGHT)
            {
                setX(gridX*gridSize + (m_frameCount-frameStartMoving)*(gridSize/movingDuration));
            }
            if(movingDirection == RIGHT)
            {
                setX(gridX*gridSize + (m_frameCount-frameStartMoving)*(gridSize/movingDuration));
            }
        }
        else
        {
            setY(gridY * gridSize);
            setX(gridX * gridSize);
        }
    }



}
