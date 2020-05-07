package com.RPG.game.phase1.entities;

import com.RPG.game.common.Entity;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Npc extends EntityRPG {

    private final float walkDuration = 10;
    private String name;

    public Npc(ArrayList<Entity> entitiesList, AssetManager assetManager, String name, int gridX, int gridY) {
        super(entitiesList, assetManager);
        this.name = name;
        setGridPosition(gridX, gridY);

        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        Animation animation_upwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Tt-3,1"),
                textureAtlas.findRegion("Tt-3,2"),
                textureAtlas.findRegion("Tt-3,3"),
                textureAtlas.findRegion("Tt-3,0"));
        Animation animation_downwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Tt-0,1"),
                textureAtlas.findRegion("Tt-0,2"),
                textureAtlas.findRegion("Tt-0,3"),
                textureAtlas.findRegion("Tt-0,0"));
        Animation animation_leftWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Tt-2,1"),
                textureAtlas.findRegion("Tt-2,2"),
                textureAtlas.findRegion("Tt-2,3"),
                textureAtlas.findRegion("Tt-2,0"));
        Animation animation_rightWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Tt-1,1"),
                textureAtlas.findRegion("Tt-1,2"),
                textureAtlas.findRegion("Tt-1,3"),
                textureAtlas.findRegion("Tt-1,0"));

        addAnimation(animation_upwardWalk);
        addAnimation(animation_rightWalk);
        addAnimation(animation_downwardWalk);
        addAnimation(animation_leftWalk);

        movingDirection = DOWN;
        setScale(2f);
        m_elapsedTime = 0.4f;
        gridSize = 64;
    }

    public String getName() {
        return name;
    }

    /**
     * actualise le comportement de l'entité
     * cette méthode est abstraite, elle sera définit dans chaques classes héritant de Entity
     * ainsi elle chacunes leurs propres comportements
     */
    @Override
    public void updateBehavior()
    {

        if(moving)
        {
            getCurrentAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }
        else
        {
            getCurrentAnimation().setPlayMode(Animation.PlayMode.NORMAL);
        }
        setAnimationIndex(movingDirection);

        updatePosition();

    }

    /**
     * Cette fonction est
     */
    public void dialog(){
        // JB remplit cette méthode stp
    }
}
