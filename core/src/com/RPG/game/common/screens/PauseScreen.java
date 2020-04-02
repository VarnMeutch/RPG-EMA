package com.RPG.game.common.screens;

import com.RPG.game.RPGMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;
    Texture img;
    SpriteBatch batch;
    Sprite sprite;


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PauseScreen(RPGMain game){
        this.game = game;
    }

    public PauseScreen(RPGMain game, Texture img) {
        this.game = game;
        batch = new SpriteBatch();
        this.sprite = new Sprite(img);
        TextButton resume = new TextButton("Resume",new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json")));
        TextButton save = new TextButton("Save",new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json")));
        TextButton options = new TextButton("Options",new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json")));
        TextButton leave = new TextButton("Leave",new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json")));
    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        //img = new Texture("inserer nom de l'image de l'écran de pause".jpg)
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
