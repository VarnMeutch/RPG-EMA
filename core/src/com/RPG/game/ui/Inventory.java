package com.RPG.game.ui;

import com.RPG.game.RPGMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Inventory {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private int HP;
    private int coins;
    private Item[] inventory;


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {  //les HP sont limités : max 100
        if (HP <= 100) {
            this.HP = HP;
        }
    }

    public Item[] getInventory() {
        return inventory;
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }


    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Cette méthode augmente le nombre de pièces du perso.
     * @param newCoins nombre de pièces supplémentaires
     */
    public void earnCoins (int newCoins) {
        coins=coins+newCoins;
    }

    /**
     * Cette méthode diminue le nombre de pièces du perso.
     * On suppose que le perso a plus de pièces qu'il n'en perd.
     * @param spentCoins nombre de pièces dépensées
     */
    public void spendCoins(int spentCoins) {
        coins=coins-spentCoins;
    }


}
