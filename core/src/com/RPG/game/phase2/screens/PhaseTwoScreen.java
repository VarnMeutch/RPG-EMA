package com.RPG.game.phase2.screens;

import com.RPG.game.RPGMain;
import com.RPG.game.common.Entity;
import com.RPG.game.phase2.entities.Player;
import com.RPG.game.phase2.entities.Projectile;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Collections;


public class PhaseTwoScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;

    SpriteBatch batch;

    Texture  m_imgRock;
    Sprite  sprite_rock;
    OrthographicCamera camera;
    private Player m_player;
    private Projectile m_projectile;

    private ArrayList<Entity> m_entitiesList;
    private AssetManager m_assetManager;

    public static final String PATH_SPRITESHEET = new String("Sprite/SpriteSheets/spriteSheet.atlas");
    public static final  String PATH_ROCK = new String("core/assets/Sprite/test-sprites/rock.png");

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PhaseTwoScreen(RPGMain game)
    {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        m_assetManager = new AssetManager();
        //on ajoute les assets à charger sur la file de l'assetManager
        m_assetManager.load(PATH_SPRITESHEET, TextureAtlas.class);
        m_assetManager.load(PATH_ROCK, Texture.class);

        //on fait charger tout les assets
        m_assetManager.finishLoading();

        //_imgRock = new Texture("core/assets/Sprite/test-sprites/rock.png");
        m_imgRock = m_assetManager.get(PATH_ROCK, Texture.class);
        sprite_rock = new Sprite(m_imgRock);
        sprite_rock.scale(2f);
        sprite_rock.setPosition(200,200);

        m_entitiesList = new ArrayList<Entity>();

        m_player = new Player(m_entitiesList, m_assetManager, camera);


        m_entitiesList.add(m_player);



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

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
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
        Collections.sort(m_entitiesList);
        for (int i=0 ; i<m_entitiesList.size(); i++)
        {
            m_entitiesList.get(i).draw(batch);
        }
        batch.end();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.line(m_player.getX()-5, m_player.getY(), m_player.getX()+5, m_player.getY());
        shapeRenderer.line(m_player.getX(), m_player.getY()-5, m_player.getX(), m_player.getY()+5);
        shapeRenderer.end();
        shapeRenderer.dispose();

    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {}

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {}

    /**
     *
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {}

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {}

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose()
    {
        batch.dispose();
        m_assetManager.dispose();
    }
}
