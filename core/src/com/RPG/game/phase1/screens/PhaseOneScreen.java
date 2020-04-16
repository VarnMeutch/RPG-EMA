package com.RPG.game.phase1.screens;

import com.RPG.game.RPGMain;
import com.RPG.game.dialogs.DialogHandler;
import com.RPG.game.phase2.entities.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Input;

public class
PhaseOneScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;
    SpriteBatch batch;
    private BitmapFont font;
    private DialogHandler diag;
    private Stage stage;
    Texture m_imgCharacter;
    OrthographicCamera camera;
    TiledMap map;
    float unitScale;
    OrthogonalTiledMapRenderer renderer;
    private Player m_player;

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PhaseOneScreen(RPGMain game) {
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
        m_imgCharacter = new Texture("core/assets/Sprite/test-sprites/npc_darkguy.png");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Skin skin = new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json"));
        diag=new DialogHandler(skin);
        float unitScale = 1 / 32f;
        OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        m_player = new Player(camera);

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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        m_player.updateBehavior();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        m_player.draw(batch);
        //renderer.setView(camera);
        //renderer.render();
        //diag.activate();
        //diag.chooseFile("Peter");
        //System.out.println("test");
        //diag.test();
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
    public void dispose() {
            batch.dispose();
            m_imgCharacter.dispose();
        }
    }
