package com.RPG.game.phase2.screens;

import com.RPG.game.RPGMain;
import com.RPG.game.common.Entity;
import com.RPG.game.common.hitbox.CircularHitBox;
import com.RPG.game.common.hitbox.HitBox;
import com.RPG.game.common.hitbox.RectHitBox;
import com.RPG.game.phase2.entities.Player;
import com.RPG.game.phase2.entities.ennemies.Bat;
import com.RPG.game.phase2.entities.projectile.FireBall;
import com.RPG.game.phase2.entities.projectile.FireBolt;
import com.RPG.game.phase2.entities.projectile.Projectile;
import com.RPG.game.phase2.ActionHUD;
import com.badlogic.gdx.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class PhaseTwoScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;

    SpriteBatch batch;

    Texture  m_imgRock;
    Sprite  sprite_rock;
    OrthographicCamera camera;
    private Player m_player;
    private ActionHUD m_hud;

    private ArrayList<Entity> m_entitiesList;
    private AssetManager m_assetManager;

    //spawner de bat
    private long m_frameCount;
    private long m_frameLastSpawn;

    public static final String PATH_SPRITESHEET = "core/assets/Sprite/SpriteSheets/spriteSheet.atlas";
    public static final  String PATH_ROCK = "core/assets/Sprite/test-sprites/rock.png";


    //test hitbox
    //HitBox hb_test1 = new RectHitBox(48,128, -24, -64);
    //CircularHitBox hb_test1 = new CircularHitBox(80, -40, -40);
    //RectHitBox hb_test2 = new RectHitBox(100,30, 0, 0);
    //CircularHitBox hb_test2 = new CircularHitBox(50,0, 0);

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PhaseTwoScreen(RPGMain game)
    {
        Gdx.graphics.setWindowedMode(1024,768);

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

        m_entitiesList = new ArrayList<>();
        m_hud = new ActionHUD(m_assetManager);

        m_player = new Player(m_entitiesList, m_assetManager, camera);
        m_player.setHUD(m_hud);
        m_entitiesList.add(m_player);
        for(int i=0; i<7; i+=1)
        {
            Bat bat = new Bat(RPGMain.random.nextInt(1000)-500 , RPGMain.random.nextInt(1000)-500, m_entitiesList, m_assetManager);

            m_entitiesList.add(bat);
        }

    }


    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show()
    {
        Gdx.graphics.setWindowedMode(1024,768);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        //on fait apparaitre une bat en appuyant sur ESPACE
        m_frameCount+=1;
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && m_frameCount > m_frameLastSpawn + 10 )
        {
            m_frameLastSpawn = m_frameCount;
            Bat bat = new Bat( m_player.getX() + RPGMain.random.nextInt(1000)-500 ,
                               m_player.getY() + RPGMain.random.nextInt(1000)-500,
                                  m_entitiesList, m_assetManager);
            m_entitiesList.add(bat);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE))
        {
            for (Entity e : m_entitiesList)
            {
                if(e instanceof Bat)
                    e.destroy();
            }
        }

        for (int i=0 ; i<m_entitiesList.size(); i++)
        {
            m_entitiesList.get(i).updateBehavior();
            if( m_entitiesList.get(i).getDestroy() )
            {
                m_entitiesList.remove(i);
                //si l'entité fait une requete de suppression, on le retire de la liste
            }
        }


        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite_rock.setPosition(200,200);
        sprite_rock.draw(batch);
        sprite_rock.setPosition(100,50);
        sprite_rock.draw(batch);
        Collections.sort(m_entitiesList);
        for (Entity entity : m_entitiesList)
        {
            entity.draw(batch);
        }
        batch.end();

        m_hud.draw();
        //m_player.drawHitBox(camera);
        /*ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 1, 1);
        shapeRenderer.line(fireBolt.getX()-5, fireBolt.getY(), fireBolt.getX()+5, fireBolt.getY());
        shapeRenderer.line(fireBolt.getX(), fireBolt.getY()-5, fireBolt.getX(), fireBolt.getY()+5);
        shapeRenderer.end();
        shapeRenderer.dispose();*/
        /*if(!hb_test1.testCollision(hb_test2, m_player.getX(), m_player.getY(), 0, 0) )
        {
            hb_test1.drawHitBox(m_player.getX(), m_player.getY(),
                    new Color(0,0,1,0.5f), camera);
        }
        else
        {
            hb_test1.drawHitBox(m_player.getX(), m_player.getY(),
                    new Color(1,0,0,0.5f), camera);
        }

        hb_test2.drawHitBox(0, 0,
                new Color(0,1,0,0.5f), camera);*/
        //if(!m_bat.getDestroy())m_bat.drawHitBox(camera);
        /*for(Entity e: m_entitiesList)
        {
            if(e instanceof FireBolt)
            {
                ((FireBolt) e).drawHitBox(camera);
            }
        }*/
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
        m_hud.dispose();
    }
}
