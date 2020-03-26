package com.rpgtest.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpgtest.game.screens.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class rpgtest extends Game {

	// --- ATTRIBUTES --------------------------------------------------------------------------------------------------
	// The screens
	private LoadingScreen loadingScreen;
	private MainMenu mainMenu;
	private LoadGameScreen loadGameScreen;
	private InventoryScreen inventoryScreen;

	private NewGameScreen newGameScreen;
	private PauseScreen pauseScreen;
	private PhaseOneScreen mainScreen;
	private PhaseTwoScreen phaseTwoScreen;
	private PreferencesScreen preferencesScreen;

	public static final int MENU = 0;
	public static final int PREFERENCES = 1;
	public static final int PAUSE = 2;
	public static final int ENDGAME = 3;

	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("on-sous-estime-souvent-le-pouvoir-d-un-sourire-surtout-sur-une-chevre_c6b01beb028d36fe15b28e1b68a1558ca9f912c0.jpg");
		font = new BitmapFont(Gdx.files.internal("Font/police1.fnt"), false);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		//batch.draw(img, 0, 0);
// affichage du texte
		//batch.begin();
		//font.draw(batch, "Hello word", 50,80);
		//batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		System.out.println("Ligne de test");
	}
}
