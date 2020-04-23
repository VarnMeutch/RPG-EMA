package com.RPG.game.common.hitbox;

public class CircularHitBox extends HitBox
{
    float m_radius;

    public CircularHitBox(float radius, float offsetX, float offsetY)
    {
        super(offsetX, offsetY);
        m_radius = radius;
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
