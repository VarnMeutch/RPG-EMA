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
	private PhaseOneScreen phaseOneScreen;
	private PhaseTwoScreen phaseTwoScreen;
	private InventoryScreen inventoryScreen;
	private PauseScreen pauseScreen;
	private PreferencesScreen preferencesScreen;

	public static final int LOAD = 0;
	public static final int MENU = 1;
	public static final int LOADGAME = 2;
	public static final int NEWGAME = 3;
	public static final int PHASEONE = 4;
	public static final int PHASETWO = 5;
	public static final int INVENTORY = 6;
	public static final int PAUSE = 7;
	public static final int PREFERENCES = 8;

	SpriteBatch batch;
	Texture img;

	/** Called when the {@link Application} is first created. */
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("on-sous-estime-souvent-le-pouvoir-d-un-sourire-surtout-sur-une-chevre_c6b01beb028d36fe15b28e1b68a1558ca9f912c0.jpg");
	}

	/** Called when the {@link Application} should render itself. */
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		System.out.println("Ligne de test");
	}
}
