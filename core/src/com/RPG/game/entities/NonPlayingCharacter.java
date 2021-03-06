package com.RPG.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class NonPlayingCharacter { //phase1

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private String name;
    private float m_x, m_y;
    private boolean m_visible;
    float m_scale;
    protected float m_elapsedTime;
    private ArrayList<Animation<TextureRegion>> m_animationList;
    private int m_animationIndex;


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Entity()
    {
        m_x=0;
        m_y=0;
        m_visible = true;
        m_scale = 1f;
        m_elapsedTime = 0f;
        m_animationIndex = 0;
        m_animationList = new ArrayList<Animation<TextureRegion>>();

    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    public void draw(SpriteBatch batch)
    {
        if(m_visible && m_animationList.size() > 0 )
        {
            m_elapsedTime += Gdx.graphics.getDeltaTime();
            TextureRegion t =  m_animationList.get(m_animationIndex).getKeyFrame(m_elapsedTime);
            batch.draw(t, m_x, m_y, t.getRegionHeight()*m_scale, t.getRegionWidth()*m_scale);
        }

    }

    /**
     * ajoute une animation à liste des animations de l'entité si newAnimation != null
     * return l'index de cette animation
     * @param newAnimation
     */
    public int addAnimation(Animation newAnimation)
    {
        if(newAnimation != null)
            m_animationList.add(newAnimation);
        return  m_animationList.size()-1;
    }

    /**
     * change l'animation actuellement jouée par l'animation d'index 'index' (l'index correspond à l'ordre d'ajout)
     * @param index
     */
    public void setAnimationIndex(int index)
    {
        if( index >= 0 && index < m_animationList.size() )
        {
            m_animationIndex = index;
        }
    }

    protected Animation getCurrentAnimation() {return m_animationList.get(m_animationIndex);}

    public void move(float deltaX, float deltaY) {m_x+=deltaX; m_y+=deltaY;}
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

    public String makeThemSpeak(String dialogue) {  //permet de faire parler le perso, il faut aller chercher le dialogue dans dialogs et c'est peut-être pas un String
        return dialogue;
    }

}