package com.RPG.game.dialogs;

import java.util.ArrayList;

public class Options {

    private ArrayList<Option> optionList;
    private ArrayList<Text> textList;

    public ArrayList<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<Option> optionList) {
        this.optionList = optionList;
    }

    public ArrayList<Text> getTextList() {
        return textList;
    }

    public void setTextList(ArrayList<Text> textList) {
        this.textList = textList;
    }

    public Options(ArrayList<Option> optionList, ArrayList<Text> textList) {
        this.optionList = optionList;
        this.textList = textList;
    }
}
