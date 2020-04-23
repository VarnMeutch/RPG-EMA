package com.RPG.game.phase2.entities.ennemies;

import com.RPG.game.common.Entity;
import com.RPG.game.common.hitbox.HitBox;
import com.RPG.game.common.hitbox.RectHitBox;
import com.RPG.game.phase2.entities.Damageable;
import com.RPG.game.phase2.entities.projectile.FireBall;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Bat extends Entity implements Damageable
{
    HitBox m_hitbox;

    public Bat(float x, float y, ArrayList<Entity> entitiesList, AssetManager assetManager)
    {
        super(entitiesList, assetManager);
        setX(x);
        setY(y);
        setZ(2f);
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        Animation animation = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("OeilVolant1"),
                textureAtlas.findRegion("OeilVolant2"),
                textureAtlas.findRegion("OeilVolant3"),
                textureAtlas.findRegion("OeilVolant4"));
        animation.setPlayMode(Animation.PlayMode.LOOP);
        addAnimation(animation);
        setScale(2f);
        m_originX = 31;
        m_originY = 7;

        m_hitbox = new RectHitBox(62*getScale(), 14*getScale(), -m_originX*getScale(), -m_originY*getScale());
    }

    @Override
    public void updateBehavior()
    {
        for (Entity e : m_entitiesList)
        {
            if(e instanceof FireBall)
            {
                if(((FireBall) e).testHit(m_hitbox, getX(), getY()))
                {
                    m_destroy = true;
                }
            }
        }
    }

    @Override
    public void takeDamage(int damage)
    {
        m_destroy = true;
    }

    public void drawHitBox(OrthographicCamera camera)
    {
        m_hitbox.drawHitBox(getX(), getY(), new Color(1,0,0,0.5f), camera);
    }
}
