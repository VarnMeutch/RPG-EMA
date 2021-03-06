package com.RPG.game.common.screens;

import com.RPG.game.RPGMain;
import com.RPG.game.phase2.entities.Player;
import com.RPG.game.ui.DialogueBox;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.RPG.game.dialogs.DialogHandler;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.RPG.game.dialogs.DialogHandler.LB;
import static com.badlogic.gdx.graphics.Color.BLACK;

public class PreferencesScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;
    private Table root = new Table();
    private DialogueBox dialogueBox;
    private DialogHandler diag;
    SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    OrthographicCamera camera;
    private Viewport gameViewport;




    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PreferencesScreen(RPGMain game) {

        this.game = game;
        gameViewport = new ScreenViewport();
        stage = new Stage();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(BLACK);
        Skin skin = new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json"));
        diag=new DialogHandler("test",skin);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);



    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            diag.isActive();
            diag.chooseFile("Peter");
            stage.addActor(diag);
            diag.settheStage(stage);
            diag.setFillParent(false);
            diag.setPosition(0,0);
            diag.setSize(Gdx.graphics.getWidth(),150);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            diag.key(Input.Keys.ENTER, 000);
        }

        stage.draw();
        stage.act(delta);
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

    }
}
