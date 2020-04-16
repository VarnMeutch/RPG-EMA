package com.RPG.game.ui;

import com.RPG.game.RPGMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Inventory {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private int PV;
    private int coins;
    private Item[] inventaire;

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {  //les PV sont limités : max 100
        if (PV <= 100) {
            this.PV = PV;
        }
    }

    public Item[] getInventaire() {
        return inventaire;
    }

    public void setInventaire(Item[] inventaire) {
        this.inventaire = inventaire;
    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Cette méthode augmente le nombre de pièces du perso.
     * @param newCoins nombre de pièces supplémentaires
     */
    public void gagnerPieces (int newCoins) {
        coins=coins+newCoins;
    }

    /**
     * Cette méthode diminue le nombre de pièces du perso.
     * On suppose que le perso a plus de pièces qu'il n'en perd.
     * @param spentCoins nombre de pièces dépensées
     */
    public void depenserPieces(int spentCoins) {
        coins=coins-spentCoins;
    }

}
