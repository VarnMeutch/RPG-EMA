package com.RPG.game.screens;

import com.RPG.game.RPGMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class InventoryScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;
    private int PV;
    private int pieces;
    private int chapo;  //nombre de potions d'invisibilité que le joueur a à sa disposition


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public InventoryScreen(RPGMain game) {
        this.game = game;
        PV=100;  //on commence le jeu à 100 PV (le maximum)
        pieces=4;  //on commence le jeu avec 4 pièces
        chapo=0;  //on commence le jeu avec O potions d'invisibilité
    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

    /**
     * Cette méthode change le nombre de PV du perso.
     * A modifier si on choisit plutôt de descendre ou de monter les PV d'une certaine valeur
     * @param nouveauPV nouvelle valeur des PV
     */
    public void setPV(int nouveauPV) {
        if ( (nouveauPV >= 0) && (nouveauPV <= 100) ) {
            PV = nouveauPV;
        }
    }

    /**
     * Cette méthode augmente le nombre de pièces du perso.
     * @param nouvellesPieces nombre de pièces supplémentaires
     */
    public void gagnerPieces (int nouvellesPieces) {
        pieces=pieces+nouvellesPieces;
    }

    /**
     * Cette méthode diminue le nombre de pièces du perso.
     * On suppose que le perso a plus de pièces qu'il n'en perd.
     * @param piecesDepensees nouvelle valeur des pièces
     */
    public void depenserPieces(int piecesDepensees) {
        pieces=pieces-piecesDepensees;
    }

    /**
     * Cette méthode augmente le nombre de potions d'invisibilité du perso.
     */
    public void gagnerChapo() {
        chapo=chapo+1;
    }

    /**
     * Cette méthode diminue le nombre de potions d'invisibilité du perso.
     * On suppose que le perso a au moins 1 chapo.
     */
    public void depenserChapo() {
        chapo=chapo-1;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

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

    }
}
