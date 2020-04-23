package com.RPG.game.common.screens;

import com.RPG.game.RPGMain;
import com.RPG.game.ui.DialogueBox;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PreferencesScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;
    private Table root;
    private DialogueBox dialogueBox;
    diag=new DialogHandler(skin);


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PreferencesScreen(RPGMain game) {
        this.game = game;
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
        diag.activate();
        diag.chooseFile("Peter");
        System.out.println("test");
        diag.test();

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        dialogueBox = new DialogueBox(new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json")));
        dialogueBox.animateText("Bienvenu aventurier!\nIci c'est l'EMA");

        root.add(dialogueBox).expand().align(Align.bottom).pad(80f);

        stage.draw();
        stage.act(delta);
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
