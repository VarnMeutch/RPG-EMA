package com.RPG.game.dialogs;

import java.util.ArrayList;


public class Line {

    private int id;
    private Options options;
    private ArrayList<Text> textList;
    private boolean endLine;

    public Line(int id, Options options,ArrayList<Text> textList, boolean endLine){
        this.id=id;
        this.options=options;
        this.textList=textList;
        this.endLine=endLine;
    }

    public boolean hasOptions(){
        if( options.getOptionList().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isFinal (){
        return endLine;
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

    public ArrayList<Text> getTexts() {
        return textList;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}
