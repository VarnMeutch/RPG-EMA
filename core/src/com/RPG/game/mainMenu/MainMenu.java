package com.RPG.game.mainMenu;

import com.RPG.game.RPGMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainMenu implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;

    private MenuControl menuControl;

    private SpriteBatch batch;
    private Texture background;

    private int background_width;
    private int background_height;

    private BitmapFont font;
    private GlyphLayout layout;

    private float frame_count;
    private int sign;

    private boolean triggered;

    private Stage stage;

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public MainMenu(RPGMain game) {
        this.game = game;

        this.background = new Texture("core/assets/Menus/MainMenu/EcranTitre.png");

        this.menuControl = new MenuControl(this);

        batch = new SpriteBatch();

        // Le truc pour créer des polices qui peuvent s'agrandir
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/Font/HARRINGT.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        // La taille de la police
        parameter.size = 60;
        // On créé notre police à l'aide de notre générateur
        font = generator.generateFont(parameter);
        // On tue notre générateur (il aura bien servi)
        generator.dispose();

        // Je veux pouvoir récupérer la taille de mon texte pour pouvoir le centrer
        layout = new GlyphLayout();
        // Je lui donne mon texte
        layout.setText(font, "Appuyez sur une touche pour commencer !");

        // Le joueur n'a pas encore appuyé sur un bouton
        triggered = false;
    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        // Je veux que mon background remplisse la fenêtre
        background_width = Gdx.graphics.getWidth();
        background_height = Gdx.graphics.getHeight();

        if (!triggered) {
            // Je veux pouvoir détecter quand le joueur appuie sur un bouton
            Gdx.input.setInputProcessor(menuControl);
        }
        else {
            Gdx.input.setInputProcessor(stage);

            batch = (SpriteBatch) stage.getBatch();

            // Create a table that holds buttons
            Table table = new Table();
            table.setFillParent(false);
            table.setPosition(0 + background_width / 2f,0 + background_height / 8f );
            stage.addActor(table);

            // Time to create buttons !
            Skin skin = new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json"));
            TextButton _continue = new TextButton("Continuer", skin);
            TextButton newGame = new TextButton("Nouveau", skin);
            TextButton preferences = new TextButton("Options", skin);

            // Add the buttons to the table
            table.add(_continue);
            table.add(newGame).space(background_width / 15f);
            table.add(preferences);

            // Give some actions to the buttons !
            _continue.addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    // TODO : Fill here
                    game.changeScreen(RPGMain.DEBUG);
                }
            });

            newGame.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    // TODO : Fill here
                    game.changeScreen(RPGMain.DEBUG);
                }
            });

            preferences.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.changeScreen(RPGMain.PREFERENCES);
                }
            });
        }

        // Clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Faire clignoter le texte
        if (frame_count == 0) {
            sign = 1;
        } else if (frame_count == 60) {
            sign = -1;
        }
        frame_count += sign;
        layout.setText(font, "Appuyez sur une touche pour commencer !", new Color(1f, 1f, 1f, frame_count / 60f), layout.width, 0, false);

        batch.begin();
        // J'affiche le background
        batch.draw(background, 0, 0, background_width, background_height);
        //batch.end();

        // J'affiche soit le texte d'accueil, soit les boutons
        if (!triggered) {
            // J'affiche le texte centré
            font.draw(batch, layout, background_width * 0.5f - layout.width * 0.5f, 100);
            batch.end();
        }
        else {
            batch.end();
            // J'affiche la table de boutons
            //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();

        }

    }

    /**
     * @param width  new width
     * @param height new height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        Gdx.graphics.setWindowedMode(width, height);
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
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        font.dispose();
        background.dispose();
        if (triggered) {
            stage.dispose();
        }
    }

    /**
     * Called when the menu screen is no longer on the "Press a button" state
     */
    public void trigger() {
        triggered = true;

        // Get events from the user
        stage = new Stage(new ScreenViewport());
        show();
    }
}

