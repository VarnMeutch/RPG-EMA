package com.RPG.game.phase2.entities;

import com.RPG.game.common.Entity;
import com.badlogic.gdx.Gdx;

public class Projectile extends Entity
{
    private float m_direction;
    private float m_lifeSpan, m_lifeTime;
    private float m_speed;
    private int m_camp; // 0 = allié, 1=ennemis 2=neutre
    private int m_damage;

    public Projectile(float direction, float speed, float lifeSpan, int damage)
    {
        m_direction = direction;
        m_speed = speed;
        m_lifeSpan = lifeSpan;
        m_damage = damage;
        m_camp = 1;
    }

    public void updateBehavior()
    {
        float delta = Gdx.graphics.getDeltaTime();
        m_lifeTime +=  delta;
        if(m_lifeTime > m_lifeSpan)
        {
            m_destroy = true;
            setVisibility(false);
        }
        else
        {
            move(m_speed*delta*(float)Math.cos(m_direction), m_speed*delta*(float)Math.sin(m_direction));
        }
    }

    public void setCamp(int camp) {m_camp  = camp;}
    public int getCamp() {return  m_camp;}


}
