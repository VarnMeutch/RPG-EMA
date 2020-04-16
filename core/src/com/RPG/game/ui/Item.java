package com.RPG.game.ui;

import com.RPG.game.RPGMain;

public class Item {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private String name;
    private int image;  // je met int parce que je ne sais pas encore comment mettre une image sur java
    private int price; // nombre de pièces que l'Item coûte
    private String description; // peut-être mettre un html à la place ?

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public Item(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() { //il faut renvoyer une image et pas un int
        return image;
    }

    public void setImage(int image) { //il faut ajouter une image et pas un int
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


// --- METHODS -----------------------------------------------------------------------------------------------------



}
