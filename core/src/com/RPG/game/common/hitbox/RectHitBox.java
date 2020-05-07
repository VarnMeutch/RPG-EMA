package com.RPG.game.common.hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RectHitBox extends HitBox
{

    float m_width, m_height;

    public RectHitBox(float width, float height, float offsetX, float offsetY)
    {
        super(offsetX, offsetY);
        m_width = width;
        m_height = height;
    }

    /**
     * ne doit pas être appelée durant un spriteBatch !!!
     * (l'utilisation de ShapeRenderer modifie les états openGL et entre en conflie avec
     * un sprite batch)
     * @param color
     * @param camera
     */
    public void drawHitBox(float x, float y, Color color, OrthographicCamera camera)
    {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x + m_offsetX, y + m_offsetY, m_width, m_height);
        shapeRenderer.end();
        shapeRenderer.dispose();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public boolean testCollision(HitBox otherHitBox, float x1, float y1, float x2, float y2)
    {
        x1+=m_offsetX;
        y1+=m_offsetY;
        x2+=otherHitBox.m_offsetX;
        y2+=otherHitBox.m_offsetY;

        if(otherHitBox instanceof RectHitBox)
        {
            if(     (x2 + ((RectHitBox) otherHitBox).m_width  > x1) && (x2 < x1 +  m_width)
                &&  (y2 + ((RectHitBox) otherHitBox).m_height > y1) && (y2 < y1 +  m_height) )
            {
                return true;
            }
        }
        if(otherHitBox instanceof CircularHitBox)
        {
            float r = ((CircularHitBox) otherHitBox).m_radius;
            //on teste d'abord si le centre de cercle est dans le rectangle
            if((x1 < x2) && (x1 + m_width > x2) && (y1 < y2) && (y1 + m_height > y2) )
                return true;
            /*
            on teste si le cercle intercepte l'un des côtés du rectangle dans le cas où le projeté
            du centre du cercle sur la droite que prolonge ce même côté appartient à ce côté
            on effectue l'oppération sur les 4 côtés
             */
            if( (x1 < x2) && (x1 + m_width > x2) ) //côtés AB et CD
            {
                if( ( Math.abs(-y1+y2) < r) ||         //côté CD
                        (Math.abs(y2-(y1+m_height)) < r) ) //côté AB
                return true;
            }
            if( (y1 < y2) && (y1 + m_height > y2) ) //côtés BC et DA
            {
                if( ( Math.abs(-x1+x2) < r) ||         //côté DA
                     (Math.abs(x2-(x1+m_width)) < r) ) //côté BC
                    return true;
            }
            //dernier cas à tester : si l'un des coins du rectangle est dans le cercle
            if(    (Math.sqrt( Math.pow(x1-x2,2) + Math.pow(y1-y2,2) ) < r )
                || (Math.sqrt( Math.pow(x1+m_width-x2,2) + Math.pow(y1-y2,2) ) < r )
                || (Math.sqrt( Math.pow(x1-x2,2) + Math.pow(y1+m_height-y2,2) ) < r )
                || (Math.sqrt( Math.pow(x1+m_width-x2,2) + Math.pow(y1+m_height-y2,2) ) < r ) )
            {
                return true;
            }
        }
        return false;
    }
}
