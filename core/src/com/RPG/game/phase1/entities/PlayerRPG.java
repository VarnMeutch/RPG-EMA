package com.RPG.game.phase1.entities;

import com.RPG.game.common.Entity;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class PlayerRPG extends EntityRPG
{
    public PlayerRPG(ArrayList<Entity> entitiesList, AssetManager assetManager)
    {
        super(entitiesList, assetManager);
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        Animation animation_upwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-4,1"),
                textureAtlas.findRegion("Perso-4,2"),
                textureAtlas.findRegion("Perso-4,3"),
                textureAtlas.findRegion("Perso-4,0"));
        Animation animation_downwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-1,2"),
                textureAtlas.findRegion("Perso-1,0"),
                textureAtlas.findRegion("Perso-1,1"),
                textureAtlas.findRegion("Perso-1,0"));
        Animation animation_leftWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-2,1"),
                textureAtlas.findRegion("Perso-2,2"),
                textureAtlas.findRegion("Perso-2,3"),
                textureAtlas.findRegion("Perso-2,0"));
        Animation animation_rightWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-3,1"),
                textureAtlas.findRegion("Perso-3,2"),
                textureAtlas.findRegion("Perso-3,3"),
                textureAtlas.findRegion("Perso-3,0"));

        addAnimation(animation_upwardWalk);
        addAnimation(animation_downwardWalk);
        addAnimation(animation_rightWalk);
        addAnimation(animation_leftWalk);

    }


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
        switch (movingDirection)
        {
            case RIGHT:
                setAnimationIndex(2);
                break;
            case LEFT:
                setAnimationIndex(3);
                break;
            case UP:
                setAnimationIndex(0);
                break;
            case DOWN:
                setAnimationIndex(1);
                break;
        }
    }
}
