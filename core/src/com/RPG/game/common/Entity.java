package com.RPG.game.common;

import com.RPG.game.phase2.entities.Projectile;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * cette classse va servir pour toutes les entités graphiques animées de la phase action
 *
 */
public abstract class Entity implements Comparable<Entity>
{
    private float m_x, m_y, m_z;
    protected boolean m_visible;
    float m_scale;
    private float m_rotation; //en rad
    protected float m_elapsedTime;
    protected float m_originX, m_originY;
    private ArrayList<Animation<TextureRegion>> m_animationList;
    private int m_animationIndex;
    protected boolean m_destroy; // indique si l'objet doit être détruit
    protected ArrayList<Entity> m_entitiesList;
    protected AssetManager m_assetManager;


    /**
     * actualise le comportement de l'entité
     * cette méthode est abstraite, elle sera définit dans chaques classes héritant de Entity
     * ainsi elle chacunes leurs propres comportements
     */
    public abstract void updateBehavior();

    public Entity(ArrayList<Entity> entitiesList, AssetManager assetManager)
    {
        m_x=0;
        m_y=0;
        m_z=0;
        m_originX = 0;
        m_originY = 0;
        m_rotation = 0f;
        m_visible = true;
        m_scale = 1f;
        m_elapsedTime = 0f;
        m_animationIndex = 0;
        m_animationList = new ArrayList<Animation<TextureRegion>>();
        m_destroy = false;
        m_entitiesList = entitiesList;
        m_assetManager = assetManager;

    }

    public void draw(SpriteBatch batch)
    {
        if(m_visible && m_animationList.size() > 0 )
        {
            m_elapsedTime += Gdx.graphics.getDeltaTime();
            TextureRegion tr =  m_animationList.get(m_animationIndex).getKeyFrame(m_elapsedTime);
            //if(this instanceof Projectile) batch.setColor(Color.GREEN);
            //batch.draw(tr, m_x - m_originX*m_scale, m_y - m_originY*m_scale, t.getRegionHeight()*m_scale, t.getRegionWidth()*m_scale);

            batch.draw(tr, m_x, m_y, m_originX*m_scale, m_originY*m_scale,
                       tr.getRegionWidth(),tr.getRegionHeight(), m_scale, m_scale, m_rotation);
            //batch.setColor(Color.WHITE);
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

    @Override
    public int compareTo(Entity entity)
    {
        if(m_z  > entity.m_z) return +1;
        if(m_z  < entity.m_z) return -1;
        return 0;
    }

    protected Animation getCurrentAnimation() {return m_animationList.get(m_animationIndex);}

    public boolean getDestroy() {return m_destroy;}

    public void move(float deltaX, float deltaY) {m_x+=deltaX; m_y+=deltaY;}
    public void setPosition(float x, float y) {m_x=x; m_y=y;}
    public void setX(float x) {m_x=x;}
    public void setY(float y) {m_y=y;}
    public void setZ(float z) {m_z=z;}
    public float getX() {return m_x;}
    public float getY() {return m_y;}
    public float getZ() {return m_z;}

    public void setVisibility(boolean visible) {m_visible = visible;}
    public boolean getVisibility() {return m_visible;}

    public void scale(float scaleFactor) {m_scale*=scaleFactor;}
    public void setScale(float newScaleFactor) {m_scale=newScaleFactor;}
    public float getScale() {return m_scale;}
    public int getAnimationIndex() {return m_animationIndex;}


}