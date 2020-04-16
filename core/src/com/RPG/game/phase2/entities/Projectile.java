package com.RPG.game.phase2.entities;

import com.RPG.game.common.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Projectile extends Entity
{
    private float m_direction;
    private float m_lifeSpan, m_lifeTime;
    private float m_speed;
    private int m_camp; // 0 = allié, 1=ennemis 2=neutre
    private int m_damage;

    public Projectile(float direction, float speed, float lifeSpan, int damage)
    {
        super();
        m_direction = direction;
        m_speed = speed;
        m_lifeSpan = lifeSpan;
        m_damage = damage;
        m_camp = 1;

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/SpriteSheets/spriteSheet.atlas"));
        Animation animation = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Feu-1"),
                textureAtlas.findRegion("Feu-2"),
                textureAtlas.findRegion("Feu-3"));
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        addAnimation(animation);
        setScale(4f);
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
            move(m_speed*(float)Math.cos(m_direction), m_speed*(float)Math.sin(m_direction));
        }
    }

    public void setCamp(int camp) {m_camp  = camp;}
    public int getCamp() {return  m_camp;}


}
