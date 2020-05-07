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

public class FireBall extends Projectile
{
    boolean m_exploding;

    public FireBall(ArrayList<Entity> entitiesList, AssetManager assetManager, float direction, float speed, long lifeSpan)
    {
        super(entitiesList, assetManager, direction, speed, lifeSpan);
        //TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/SpriteSheets/spriteSheet.atlas"));
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        Animation animation = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("bouleFeu1"),
                textureAtlas.findRegion("bouleFeu2"),
                textureAtlas.findRegion("bouleFeu3"),
                textureAtlas.findRegion("bouleFeu4"));
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        addAnimation(animation);
        Animation animationE = new Animation<TextureRegion>(0.07f,
                textureAtlas.findRegion("FeuExplo1"),
                textureAtlas.findRegion("FeuExplo2"),
                textureAtlas.findRegion("FeuExplo3"));
        animationE.setPlayMode(Animation.PlayMode.NORMAL);
        addAnimation(animationE);
        setScale(1.5f);
        m_originX = textureAtlas.findRegion("bouleFeu1").getRegionWidth()/2f;
        m_originY = textureAtlas.findRegion("bouleFeu1").getRegionHeight()/2f;

        setRotation((float) (m_direction*180f/Math.PI));
        m_hitBox = new CircularHitBox(16*getScale(), 0, 0);
        m_exploding = false;
    }

    public void updateBehavior()
    {
        if(m_frameCount > m_lifeSpan)
        {
            if( !m_exploding) // la boule de feu vient d'arriver à ça fin de vie
            {
                m_exploding = true;
                m_elapsedTime = 0;
                setAnimationIndex(1);
                m_speed = 0;
                scale(2f);
                setZ(2.5f);
                m_hitBox = new CircularHitBox(16*getScale(), 0, 0);
            }
            else
            {
                if(getCurrentAnimation().isAnimationFinished(m_elapsedTime)) //l'animation de destruction est finie
                {
                    m_destroy = true;
                    setVisibility(false);
                }
            }
        }
        else
        {
            move(m_speed*(float)Math.cos(m_direction), m_speed*(float)Math.sin(m_direction));
        }
    }

    public void drawHitBox(OrthographicCamera camera)
    {
        m_hitBox.drawHitBox(getX(), getY(), new Color(0,0.5f,0.5f,0.5f), camera);
    }

    public boolean getExploding() {return m_exploding;}
}
