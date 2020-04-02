package com.RPG.game.entities;

import com.RPG.game.ui.Item;

public class Object {  // classe où l'on va mettre les meubles et autres ?

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------

    private int image; // je met int parce que je ne sais pas encore comment mettre une image sur java
    private int posX; // on code la position "horizontale" de l'objet
    private int posY; // on code la position "verticale" de l'objet

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPosX() {
        return (posX);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    // --- METHODS -----------------------------------------------------------------------------------------------------

}