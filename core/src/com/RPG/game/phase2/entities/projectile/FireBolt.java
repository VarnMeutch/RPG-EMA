package com.RPG.game.phase2.entities.projectile;

import com.RPG.game.common.Entity;
import com.RPG.game.common.hitbox.CircularHitBox;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class FireBolt extends Projectile
{


    public FireBolt(ArrayList<Entity> entitiesList, AssetManager assetManager, float direction, float speed, long lifeSpan)
    {
        super(entitiesList, assetManager, direction, speed, lifeSpan);
        //TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/SpriteSheets/spriteSheet.atlas"));
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        Animation animation = new Animation<TextureRegion>(1/5f,
                textureAtlas.findRegion("green_firebolt0"),
                textureAtlas.findRegion("green_firebolt1"),
                textureAtlas.findRegion("green_firebolt2"),
                textureAtlas.findRegion("green_firebolt3"));
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        addAnimation(animation);
        m_originX = 16;
        m_originY = 24;
        setScale(2f);
        setRotation(-90+(float) ((m_direction)*180f/Math.PI));
        //setColor(new Color(0f, 0f, 0f, 1f));
        m_hitBox = new CircularHitBox(4*getScale(), 0, 0);
    }

    public void updateBehavior()
    {
        if(m_frameCount > m_lifeSpan)
        {
                m_destroy = true;
        }

        move(m_speed*(float)Math.cos(m_direction), m_speed*(float)Math.sin(m_direction));

    }

    public void drawHitBox(OrthographicCamera camera)
    {
        m_hitBox.drawHitBox(getX(), getY(), new Color(1f,0,0,0.5f), camera);
    }
}

