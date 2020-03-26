package com.RPG.game;


import com.RPG.game.screens.*;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RPGMain extends Game {

	// --- ATTRIBUTES --------------------------------------------------------------------------------------------------
	// The screens
	private LoadingScreen loadingScreen;
	private MainMenu mainMenu;
	private LoadGameScreen loadGameScreen;
	private NewGameScreen newGameScreen;
	private PhaseOneScreen mainScreen;
	private InventoryScreen inventoryScreen;
	private PauseScreen pauseScreen;
	private PhaseOneScreen phaseOneScreen;
	private PhaseTwoScreen phaseTwoScreen;
	private PreferencesScreen preferencesScreen;

	public static final int MENU = 0;
	public static final int PREFERENCES = 1;
	public static final int PAUSE = 2;
	public static final int ENDGAME = 3;

	SpriteBatch batch;
	Texture img;

	/** Called when the {@link Application} is first created. */
	@Override
	public void create () {
		/*batch = new SpriteBatch();
		img = new Texture("on-sous-estime-souvent-le-pouvoir-d-un-sourire-surtout-sur-une-chevre_c6b01beb028d36fe15b28e1b68a1558ca9f912c0.jpg");*/
		phaseTwoScreen = new PhaseTwoScreen();
		setScreen(phaseTwoScreen);
	}

	/** Called when the {@link Application} should render itself. */
	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		System.out.println("Ligne de test");
	}
}
