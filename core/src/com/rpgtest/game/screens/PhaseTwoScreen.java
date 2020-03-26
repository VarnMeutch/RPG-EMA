package com.rpgtest.game.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PhaseTwoScreen implements Screen {

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    SpriteBatch batch;
    Texture m_imgCharacter, m_imgRock;
    Sprite sprite, sprite_rock;
    OrthographicCamera camera;

    public PhaseTwoScreen()
    {
        batch = new SpriteBatch();
        m_imgCharacter = new Texture("npc_darkguy.png");
        m_imgRock = new Texture("rock.png");
        sprite = new Sprite(m_imgCharacter);
        sprite_rock = new Sprite(m_imgRock);
        sprite.scale(2f);
        //sprite.setCenter(16,16);
        sprite_rock.scale(2f);
        sprite_rock.setPosition(200,200);
    }

    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
