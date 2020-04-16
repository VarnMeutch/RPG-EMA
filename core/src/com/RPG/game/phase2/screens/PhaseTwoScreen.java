package com.RPG.game.phase2.screens;

import com.RPG.game.RPGMain;
import com.RPG.game.common.Entity;
import com.RPG.game.phase2.entities.Player;
import com.RPG.game.phase2.entities.Projectile;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;


public class PhaseTwoScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;

    SpriteBatch batch;
    Texture m_imgCharacter, m_imgRock;
    Sprite  sprite_rock;
    OrthographicCamera camera;
    private Player m_player;
    private Projectile m_projectile;

    private ArrayList<Entity> m_entitiesList;

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

        m_entitiesList = new ArrayList<Entity>();

        m_player = new Player(m_entitiesList, camera);

        m_projectile = new Projectile(m_entitiesList,.5f*(float)Math.PI, 4f, 20f, 20);


        m_entitiesList.add(m_player);
        m_entitiesList.add(m_projectile);



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

        Gdx.gl.glClearColor(64, 64, 64, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (int i=0 ; i<m_entitiesList.size(); i++)
        {
            m_entitiesList.get(i).updateBehavior();
            if( m_entitiesList.get(i).getDestroy() )
            {
                m_entitiesList.remove(i);
                //si l'entité fait une requete de suppression, on le retire de la liste
            }
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite_rock.setPosition(200,200);
        sprite_rock.draw(batch);
        sprite_rock.setPosition(100,50);
        sprite_rock.draw(batch);
        for (int i=0 ; i<m_entitiesList.size(); i++)
        {
            m_entitiesList.get(i).draw(batch);
        }

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
     *
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
