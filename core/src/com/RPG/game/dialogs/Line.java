package com.RPG.game.dialogs;

import java.util.ArrayList;

public class Line {

    private int id;
    private Options options;
    private ArrayList<Text> textList;

    public Line(int id, Options options,ArrayList<Text> textList){
        this.id=id;
        this.options=options;
        this.textList=textList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}
