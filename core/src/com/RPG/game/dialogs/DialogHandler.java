package com.RPG.game.dialogs;

import java.io.File;

public class DialogHandler {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------

    private String npcName;
    private File currentFile;

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public DialogHandler(){

    }


    // --- METHODS -----------------------------------------------------------------------------------------------------

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }


    public String getNpcName() {
        return npcName;
    }

    public void setNpcName(String npcName) {
        this.npcName = npcName;
    }
}
