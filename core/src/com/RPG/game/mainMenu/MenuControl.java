package com.RPG.game.mainMenu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MenuControl extends InputAdapter {

    private MainMenu mainMenu;

    public MenuControl(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    @Override
    public boolean keyDown(int keycode) {
            mainMenu.trigger();
            return true;
    }
}
