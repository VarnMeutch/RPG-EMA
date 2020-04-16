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


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public MainMenu(RPGMain game) {
        this.game = game;

        this.menuControl = new MenuControl(this);

        this.background = new Texture("core/assets/Menus/MainMenu/EcranTitre.png");

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

        // Je veux pouvoir détecter quand le joueur appuie sur un bouton
        Gdx.input.setInputProcessor(menuControl);

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
        batch.begin();
        // J'affiche le background
        batch.draw(background, 0, 0, background_width, background_height);
        // J'affiche le texte centré
        font.draw(batch, layout, background_width * 0.5f  - layout.width * 0.5f, 100);
        batch.end();
    }

    /**
     * @param width new width
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

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        font.dispose();
    }

    /**
     * Called when the menu screen is no longer on the "Press a button" state
     */
    public void trigger(){
        // this.game.changeScreen(new MenuScreen2(this.game));
        System.out.println("Triggered");
    }
}