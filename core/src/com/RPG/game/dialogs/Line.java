package com.RPG.game.dialogs;

public class Line {

    private int id;
    private Options options;
    private Text text;

    public Line(int id, Options options,Text text){
        this.id=id;
        this.options=options;
        this.text=text;
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
