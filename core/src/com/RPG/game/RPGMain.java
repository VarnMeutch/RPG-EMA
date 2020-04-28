package com.RPG.game;

import com.RPG.game.mainMenu.MainMenu;
import com.RPG.game.common.screens.*;
import com.RPG.game.phase1.screens.PhaseOneScreen;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

import java.util.ArrayList;
import java.util.Random;

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
	private SaveScreen saveScreen;
	private DebugScreen debugScreen;

	public static final int LOADING = 0;
	public static final int MAINMENU = 1;
	public static final int LOADGAME = 2;
	public static final int NEWGAME = 3;
	public static final int PHASEONE = 4;
	public static final int PHASETWO = 5;
	public static final int INVENTORY = 6;
	public static final int PAUSE = 7;
	public static final int PREFERENCES = 8;
	public static final int SAVE = 9;
	public static final int DEBUG = 10;

	public ArrayList<Integer> screenHistory;

	public static Random random = new Random();



	// --- METHODS -----------------------------------------------------------------------------------------------------

	/**
	 * Go to another screen
	 * @param screen the screen we're changing for
	 */
	public void changeScreen(int screen){
		switch(screen){
			case LOADING:
				if(loadingScreen == null) loadingScreen = new LoadingScreen(this);
				this.setScreen(loadingScreen);
				break;
			case MAINMENU:
				mainMenu = new MainMenu(this);
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
				this.setScreen(phaseTwoScreen);
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
			case SAVE:
				if(saveScreen == null) saveScreen = new SaveScreen(this);
				this.setScreen(saveScreen);
				break;
			case DEBUG:
				if(debugScreen == null) debugScreen = new DebugScreen(this);
				this.setScreen(debugScreen);
				break;
		}
		// Storing the screens
		if (screenHistory.size() > 1 && screenHistory.get(screenHistory.size() - 2) == screen){
			// If we go back to the previous screen, we just remove the old screen from the pile
			screenHistory.remove(screenHistory.size() - 1);
		} else {
			screenHistory.add(screen);
		}
	}

	public int previousScreen(){
		return screenHistory.get(screenHistory.size() - 2);
	}

	/**
	 * Called when the {@link  Application}is first created.
	 */
	@Override
	public void create ()
	{
		screenHistory = new ArrayList<>();
		Gdx.graphics.setWindowedMode(1024,768); // pour changer la taille de la fenetre
		changeScreen(DEBUG);

	}
}
