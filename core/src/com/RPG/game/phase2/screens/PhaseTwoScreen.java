package com.RPG.game.phase2.screens;

import com.RPG.game.phase2.entities.Entity;

import com.RPG.game.RPGMain;
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

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;

    SpriteBatch batch;
    Texture m_imgCharacter, m_imgRock;
    Sprite  sprite_rock;
    OrthographicCamera camera;
    private Entity m_player;

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PhaseTwoScreen(RPGMain game)
    {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        m_imgCharacter = new Texture("core/assets/Sprite/test-sprites/npc_darkguy.png");
        m_imgRock = new Texture("Sprite/test-sprites/rock.png");
        sprite_rock = new Sprite(m_imgRock);
        sprite_rock.scale(2f);
        sprite_rock.setPosition(200,200);

        m_player = new Entity();
        m_player.setPosition(0,0);
        m_player.scale(3f);
    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            m_player.move(5, 0);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            m_player.move(-5, 0);
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            m_player.move(0, 5);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            m_player.move(0, -5);

        camera.position.set(m_player.getX()  + 48, m_player.getY()  + 48, 0);
        //sprite.setPosition(0,0);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite_rock.setPosition(200,200);
        sprite_rock.draw(batch);
        sprite_rock.setPosition(100,50);
        sprite_rock.draw(batch);
        m_player.draw(batch);
        batch.end();
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
    public void dispose()
    {
        batch.dispose();
        m_imgCharacter.dispose();
    }
}
