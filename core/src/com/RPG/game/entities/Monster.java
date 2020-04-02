package com.RPG.game.entities;

public class Monster { // phase2

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------

    private int image;  // je met int parce que je ne sais pas encore comment mettre une image sur java
    private String description; // peut-être mettre un html à la place ? c'est un petit speech (facultatif)
                                // pour expliquer (ou pas) au joueur ce qui se passe
    private int posX; // on code la position "horizontale" de l'objet
    private int posY; // on code la position "verticale" de l'objet

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void monsterFight() { // réactions du monstre à la position et aux actions du joueur

    }

}
