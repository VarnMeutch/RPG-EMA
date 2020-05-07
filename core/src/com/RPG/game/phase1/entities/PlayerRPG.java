package com.RPG.game.phase1.entities;

import com.RPG.game.common.Entity;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class PlayerRPG extends EntityRPG
{
    private final float walkDuration = 10;
    private Camera camera;



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
        addAnimation(animation_rightWalk);
        addAnimation(animation_downwardWalk);
        addAnimation(animation_leftWalk);


        movingDirection = DOWN;
        setScale(2f);
        m_elapsedTime = 0.4f;
        gridSize = 64;

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
        setAnimationIndex(movingDirection);

        if(!moving)
        {
            if( Gdx.input.isKeyPressed(Input.Keys.Z))
            {
                moveTo(UP, walkDuration);
            }
            else if( Gdx.input.isKeyPressed(Input.Keys.S))
            {
                moveTo(DOWN, walkDuration);
            }
            else if( Gdx.input.isKeyPressed(Input.Keys.Q))
            {
                moveTo(LEFT, walkDuration);
            }
            else if( Gdx.input.isKeyPressed(Input.Keys.D))
            {
                moveTo(RIGHT, walkDuration);
            }

        }

        updatePosition();

        if(camera!=null)
        {
            camera.position.set(getX()  + 48, getY()  + 48, 0);
        }
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }
}
