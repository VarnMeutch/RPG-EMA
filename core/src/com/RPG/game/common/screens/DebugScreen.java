package com.RPG.game.common.screens;

import com.RPG.game.RPGMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class DebugScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;
    private final Stage stage;


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public DebugScreen(RPGMain game) {
        this.game = game;

        // Get events from the user
        stage = new Stage(new ScreenViewport());

    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that holds buttons
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        // Time to create buttons !
        Skin skin = new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json"));
        TextButton loading = new TextButton("Loading", skin);
        TextButton loadGame = new TextButton("LoadGame", skin);
        TextButton newGame = new TextButton("NewGame", skin);
        TextButton phaseOne = new TextButton("PhaseOne", skin);
        TextButton phaseTwo = new TextButton("PhaseTwo", skin);
        TextButton inventory = new TextButton("Inventory", skin);
        TextButton pause = new TextButton("Pause", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);

        // Put them on the table !
        table.add(loading);
        table.add(loadGame);
        table.row().pad(10, 10, 10, 10);
        table.add(inventory);
        table.add(newGame);
        table.row().pad(10, 10, 10, 10);
        table.add(pause);
        table.add(phaseOne);
        table.row().pad(10, 10, 10, 10);
        table.add(preferences);
        table.add(phaseTwo);
        table.row().pad(10, 10, 10, 10);
        table.add(exit).colspan(2);


        // Give some actions to the buttons !
        loading.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.LOADING);
            }
        });

        loadGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.LOADGAME);
            }
        });


        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.NEWGAME);
            }
        });

        phaseOne.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.PHASEONE);
            }
        });

        phaseTwo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.PHASETWO);
            }
        });

        inventory.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.INVENTORY);
            }
        });

        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.PAUSE);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(RPGMain.PREFERENCES);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * @param width new width
     * @param height new height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);    }

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
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

}

