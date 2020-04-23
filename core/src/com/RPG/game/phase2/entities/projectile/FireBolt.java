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
    public FireBolt(ArrayList<Entity> entitiesList, AssetManager assetManager, float direction, float speed, float lifeSpan)
    {
        super(entitiesList, assetManager, direction, speed, lifeSpan);
        //TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/SpriteSheets/spriteSheet.atlas"));
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        Animation animation = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("firebolt0"),
                textureAtlas.findRegion("firebolt1"),
                textureAtlas.findRegion("firebolt2"),
                textureAtlas.findRegion("firebolt3"));
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        addAnimation(animation);
        setScale(1.5f);
        m_originX = 16;
        m_originY = 8;

        setRotation((float) (m_direction*180f/Math.PI));
        m_hitbox = new CircularHitBox(10*getScale(), 0, 0);
    }

    public void updateBehavior()
    {

        float delta = Gdx.graphics.getDeltaTime();
        m_lifeTime +=  delta;
        if(m_lifeTime > m_lifeSpan) {
            if (getAnimationIndex() == 0) // la boule de feu vient d'arriver à ça fin de vie
            {
                m_destroy = true;
            }
        }

        move(m_speed*(float)Math.cos(m_direction), m_speed*(float)Math.sin(m_direction));

    }

    public void drawHitBox(OrthographicCamera camera)
    {
        m_hitbox.drawHitBox(getX(), getY(), new Color(0,0.5f,0.5f,0.5f), camera);
    }
}

