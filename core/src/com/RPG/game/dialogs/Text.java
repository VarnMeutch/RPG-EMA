package com.RPG.game.dialogs;

public class  Text {
    private String text;
    private String talkingName;

    public Text(String text,String npcName){
        this.text=text;
        this.talkingName=npcName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTalkingName(){return talkingName;}


}
