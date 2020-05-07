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
        setY(gridX * gridSize);
        moving = false;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
        setY(gridY * gridSize);
        moving = false;
    }

    public void setGridPosition(int gridX, int gridY){
        setGridX(gridX);
        setGridX(gridY);
        moving = false;
    }

    // --------------------------- Methods -------------------------------------------------------------------



}
