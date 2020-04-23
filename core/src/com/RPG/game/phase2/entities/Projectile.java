package com.RPG.game.phase2.entities;

import com.RPG.game.common.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Projectile extends Entity
{
    private float m_direction;
    private float m_lifeSpan, m_lifeTime;
    private float m_speed;
    private int m_camp; // 0 = allié, 1=ennemis 2=neutre
    private int m_damage;
    //private Entity m_boundEntity;

    public Projectile(ArrayList<Entity> entitiesList, AssetManager assetManager, float direction, float speed, float lifeSpan, int damage)
    {
        super(entitiesList, assetManager);
        m_direction = direction;
        m_speed = speed;
        m_lifeSpan = lifeSpan;
        m_damage = damage;
        m_camp = 1;
        //m_boundEntity = null;


        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/SpriteSheets/spriteSheet.atlas"));
        Animation animation = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("bouleFeu1"),
                textureAtlas.findRegion("bouleFeu2"),
                textureAtlas.findRegion("bouleFeu3"),
                textureAtlas.findRegion("bouleFeu4"));
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        addAnimation(animation);
        Animation animationE = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("FeuExplo1"),
                textureAtlas.findRegion("FeuExplo2"),
                textureAtlas.findRegion("FeuExplo3"));
        animationE.setPlayMode(Animation.PlayMode.NORMAL);
        addAnimation(animationE);
        setScale(1.5f);
        m_originX = textureAtlas.findRegion("bouleFeu1").getRegionWidth()/2;
        m_originY = textureAtlas.findRegion("bouleFeu1").getRegionHeight()/2;

        setRotation((float) (m_direction*180f/Math.PI));

    }

    public void updateBehavior()
    {

        float delta = Gdx.graphics.getDeltaTime();
        m_lifeTime +=  delta;
        if(m_lifeTime > m_lifeSpan)
        {
            if(getAnimationIndex() == 0) // la boule de feu vient d'arriver à ça fin de vie
            {
                m_elapsedTime = 0;
                setAnimationIndex(1);
                m_speed = 0;
                scale(1.5f);
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

    public void setCamp(int camp) {m_camp  = camp;}
    public int getCamp() {return  m_camp;}

    /*public Entity getBoundEntity() {return m_boundEntity;}
    public void setBoundEntity(Entity boundEntity) {m_boundEntity = boundEntity;}*/


}
