package com.RPG.game.phase2.entities.projectile;

import com.RPG.game.common.Entity;
import com.RPG.game.common.hitbox.HitBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public abstract class Projectile extends Entity
{
    protected float m_direction;
    protected long m_lifeSpan; //durée de vie du projectile en frame
    protected float m_speed;
    protected HitBox m_hitBox;

    public Projectile(ArrayList<Entity> entitiesList, AssetManager assetManager, float direction, float speed, long lifeSpan)
    {
        super(entitiesList, assetManager);
        m_direction = direction;
        m_speed = speed;
        m_lifeSpan = lifeSpan;
    }

    public boolean testHit(HitBox target_hb, float target_x, float target_y)
    {
        return m_hitBox.testCollision(target_hb, getX(), getY(), target_x, target_y);
    }

}
