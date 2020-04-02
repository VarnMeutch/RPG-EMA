package com.RPG.game.ui;

import com.RPG.game.RPGMain;

public class Item {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private String name;
    private int image;  // je met int parce que je ne sais pas encore comment mettre une image sur java
    private int quantity;  // pour savoir combien d'occurrences de l'Item il y a
    private int price; // nombre de pièces que l'Item coûte
    private String description; // peut-être mettre un html à la place ?

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    /**
     * Cette méthode augmente la quantité d'un Item
     */
    public void gagnerItem() {
        quantity=quantity+1;
    }

    /**
     * Cette méthode diminue la quantité d'un Item
     * On suppose que le perso a au moins 1 occurrence de cet Item
     */
    public void depenserItem() {
        quantity=quantity-1;
    }

}
