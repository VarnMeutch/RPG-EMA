package com.RPG.game.ui;

import com.RPG.game.RPGMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private int HP;
    private int coins;
    private List<Item> inventory;


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public Inventory(int HP, int coins) {
        this.HP = HP;
        this.coins = coins;
        this.inventory = new ArrayList<Item>();
    }
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

    public List<Item> getInventory() {
        return inventory;
    }

    public void earnItem(Item item) {
            this.inventory.add(item);
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

    public void useItem(Item item) {
        inventory.remove(item);
        // effet de l'item à effectuer
    }
}
