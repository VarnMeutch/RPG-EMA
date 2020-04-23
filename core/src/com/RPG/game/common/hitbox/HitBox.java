package com.RPG.game.common.hitbox;

public abstract class HitBox
{
    float m_offsetX, m_offsetY;
    public HitBox(float offsetX, float offsetY)
    {
        m_offsetX = offsetX;
        m_offsetY = offsetY;
    }

    /**
     *
     * @param otherHitBox
     * @param x1 coord x de cette hitbox
     * @param y1 coord y de cette hitbox
     * @param x2 coord x de l'hitbox passée en paramètre
     * @param y2 coord y de l'hitbox passée en paramètre
     * @return true si il y a collision
     */
    public abstract boolean testCollision(HitBox otherHitBox, float x1, float y1, float x2, float y2);
}

