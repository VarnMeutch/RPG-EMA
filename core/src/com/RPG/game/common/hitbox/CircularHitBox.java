package com.RPG.game.common.hitbox;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CircularHitBox extends HitBox
{
    float m_radius;

    public CircularHitBox(float radius, float offsetX, float offsetY)
    {
        super(offsetX, offsetY);
        m_radius = radius;
    }

    public void drawHitBox(float x, float y, Color color, OrthographicCamera camera)
    {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x + m_offsetX, y +m_offsetY, m_radius);
        shapeRenderer.end();
        shapeRenderer.dispose();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public boolean testCollision(HitBox otherHitBox, float x1, float y1, float x2, float y2)
    {
        if(otherHitBox instanceof CircularHitBox)
        {
            x1+=m_offsetX;
            y1+=m_offsetY;
            x2+=otherHitBox.m_offsetX;
            y2+=otherHitBox.m_offsetY;
            if(Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ) < m_radius + ((CircularHitBox) otherHitBox).m_radius)
            {
                return true;
            }
        }
        if(otherHitBox instanceof RectHitBox)
        {
            return otherHitBox.testCollision(this, x2, y2, x1, y1);
        }

        return false;
    }
}
