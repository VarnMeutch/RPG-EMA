package com.RPG.game;


import com.RPG.game.screens.*;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

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

	public static final int LOADING = 0;
	public static final int MAINMENU = 1;
	public static final int LOADGAME = 2;
	public static final int NEWGAME = 3;
	public static final int PHASEONE = 4;
	public static final int PHASETWO = 5;
	public static final int INVENTORY = 6;
	public static final int PAUSE = 7;
	public static final int PREFERENCES = 8;

	SpriteBatch batch;
	Texture img;
	BitmapFont font;

	// --- METHODS -----------------------------------------------------------------------------------------------------

	/**
	 * Go to another screen
	 * @param screen
	 */
	public void changeScreen(int screen){
		switch(screen){
			case LOADING:
				if(loadingScreen == null) loadingScreen = new LoadingScreen(this);
				this.setScreen(loadingScreen);
				break;
			case MAINMENU:
				if(mainMenu == null) mainMenu = new MainMenu(this);
				this.setScreen(mainMenu);
				break;
			case LOADGAME:
				if(loadGameScreen == null) loadGameScreen = new LoadGameScreen(this);
				this.setScreen(loadGameScreen);
				break;
			case NEWGAME:
				if(newGameScreen == null) newGameScreen = new NewGameScreen(this);
				this.setScreen(newGameScreen);
				break;
			case PHASEONE:
				if(phaseOneScreen == null) phaseOneScreen = new PhaseOneScreen(this);
				this.setScreen(phaseOneScreen);
				break;
			case PHASETWO:
				if(phaseTwoScreen == null) phaseTwoScreen = new PhaseTwoScreen(this);
				this.setScreen(phaseOneScreen);
				break;
			case INVENTORY:
				if(inventoryScreen == null) inventoryScreen = new InventoryScreen(this);
				this.setScreen(inventoryScreen);
				break;
			case PAUSE:
				if(pauseScreen == null) pauseScreen = new PauseScreen(this);
				this.setScreen(pauseScreen);
				break;
			case PREFERENCES:
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				break;
		}
	}

	/**
	 * Called when the {@link Application} is first created.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("on-sous-estime-souvent-le-pouvoir-d-un-sourire-surtout-sur-une-chevre_c6b01beb028d36fe15b28e1b68a1558ca9f912c0.jpg");
		font = new BitmapFont(Gdx.files.internal("Font/police1.fnt"), false);
		/*batch = new SpriteBatch();
		img = new Texture("on-sous-estime-souvent-le-pouvoir-d-un-sourire-surtout-sur-une-chevre_c6b01beb028d36fe15b28e1b68a1558ca9f912c0.jpg");*/
		phaseTwoScreen = new PhaseTwoScreen(this);
		setScreen(phaseTwoScreen);
	}

	/** Called when the {@link Application} should render itself. */
	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		font.draw(batch, "Coucou tout le monde !", 50,80);
		batch.end();*/

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		System.out.println("Ligne de test");
	}
}
