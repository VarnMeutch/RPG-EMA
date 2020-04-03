package com.RPG.game.phase2.entities;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * cette classse va servir pour toutes les entités graphiques animées de la phase action
 *
 */
public class Entity
{
    private float m_x, m_y;
    private Animation m_animation;
    private boolean m_visible;
    float m_scale;
    TextureAtlas m_textureAtlas;

    public Entity()
    {
        m_x=0;
        m_y=0;
        m_visible = true;
        m_scale = 1f;

        m_textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/test-sprites/spriteSheetTest.atlas"));
        m_animation = new Animation<TextureRegion>(1/15f,
                m_textureAtlas.findRegion("Actor2-0,0"),
                m_textureAtlas.findRegion("Actor2-1,0"),
                m_textureAtlas.findRegion("Actor2-2,0"));
        //m_animation.setPlayMode(Animation.PlayMode.LOOP);

    }

    public void draw(SpriteBatch batch)
    {
        if(m_visible && m_animation!= null)
        {
            TextureRegion t =  (TextureRegion)m_animation.getKeyFrame(Gdx.graphics.getDeltaTime(),true);
            batch.draw(t, m_x, m_y, t.getRegionHeight()*m_scale, t.getRegionWidth()*m_scale);

        }

    }

    public void move(float deltaX, float deltaY)
    {
        m_x+=deltaX;
        m_y+=deltaY;
    }

    public void setPosition(float x, float y) {m_x=x; m_y=y;}
    public void setX(float x) {m_x=x;}
    public void setY(float y) {m_y=y;}
    public float getX() {return m_x;}
    public float getY() {return m_y;}

    public void setVisibility(boolean visible) {m_visible = visible;}
    public boolean getVisibility() {return m_visible;}

    public void scale(float scaleFactor) {m_scale*=scaleFactor;}
    public void setScale(float newScaleFactor) {m_scale=newScaleFactor;}
    public float getScale() {return m_scale;}
}
