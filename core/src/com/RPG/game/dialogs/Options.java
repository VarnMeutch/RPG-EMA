package com.RPG.game.dialogs;

import java.util.ArrayList;

public class Options {

    private ArrayList<Option> optionList;

    public ArrayList<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<Option> optionList) {
        this.optionList = optionList;
    }


    public Options(ArrayList<Option> optionList) {
        this.optionList = optionList;
    }
}
