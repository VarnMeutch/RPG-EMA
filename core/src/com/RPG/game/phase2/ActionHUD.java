package com.RPG.game.phase2;

import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class ActionHUD implements Disposable
{
    Sprite m_spriteHealthBar, m_spriteHealthBarHalf;
    SpriteBatch m_batch;
    int m_health=5;
    final private float m_scaleBar=1.5f;

    public ActionHUD(AssetManager assetManager)
    {
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        m_spriteHealthBar = textureAtlas.createSprite("healthBar_full");
        m_spriteHealthBarHalf = textureAtlas.createSprite("healthBar_half");
        m_spriteHealthBar.setOrigin(0, 16);
        m_spriteHealthBarHalf.setOrigin(0, 16);
        m_batch = new SpriteBatch();
        m_spriteHealthBar.scale(m_scaleBar - 1f);
        m_spriteHealthBarHalf.scale(m_scaleBar - 1f);
    }

    public void draw()
    {
        m_spriteHealthBar.setY(Gdx.graphics.getHeight() - m_spriteHealthBar.getHeight() - 8);
        m_spriteHealthBarHalf.setY( Gdx.graphics.getHeight() - m_spriteHealthBar.getHeight() - 8);

        m_batch.begin();
        int i=0;
        for( ; i<m_health/2 && i <20; i+=1)
        {
            m_spriteHealthBar.setX(8 + i*(m_spriteHealthBar.getWidth()*m_scaleBar + 8));
            m_spriteHealthBar.draw(m_batch);
        }
        if(m_health%2 == 1)
        {
            m_spriteHealthBarHalf.setX(8 + i*(m_spriteHealthBar.getWidth()*m_scaleBar + 8));
            m_spriteHealthBarHalf.draw(m_batch);
        }

        m_batch.end();
    }

    public void update(int health)
    {
        m_health = health;
    }

    @Override
    public void dispose()
    {
        m_batch.dispose();
    }
}
