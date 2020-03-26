package com.RPG.game.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseScreen implements Screen {

    Texture img;
    SpriteBatch batch;
    Sprite sprite;
    Game game;


    public PauseScreen(Game game, Texture img) {
        this.game = game;
        batch = new SpriteBatch();
        this.sprite = new Sprite(img);
    }

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
        //TextButton resume = new TextButton("Resume");
        //TextButton save = new TextButton("Save");
        //TextButton options = new TextButton("Options");
        //TextButton leave = new TextButton("Leave");

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
