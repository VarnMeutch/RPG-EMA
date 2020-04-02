package com.RPG.game.entities;

public class NonPlayingCharacter { //phase1

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private String name;
    private int photo;  // je met int parce que je ne sais pas encore comment mettre une image sur java, mais ça doit représenter le pnj
    private int posX; // on code la position "horizontale" de l'objet
    private int posY; // on code la position "verticale" de l'objet

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
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

    public String makeThemSpeak(String dialogue) {  //permet de faire parler le perso, il faut aller chercher le dialogue dans dialogs et c'est peut-être pas un String
        return dialogue;
    }

}
