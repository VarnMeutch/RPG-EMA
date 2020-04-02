package com.RPG.game.dialogs;

public class Option {

    private int action;
    private String text;

    public Option (int action,String text) {
        this.action=action;
        this.text=text;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
