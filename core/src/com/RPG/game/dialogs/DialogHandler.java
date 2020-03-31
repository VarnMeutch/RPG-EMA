package com.RPG.game.dialogs;

import org.w3c.dom.Node;

import java.io.File;

public class DialogHandler {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------

    private String npcName;
    private File currentFile;
    private boolean isActive;
    private ConversationHandler conv;

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public DialogHandler(){
        isActive=false;
    }


    // --- METHODS -----------------------------------------------------------------------------------------------------

    public void chooseFile (String npcName){
        this.npcName=npcName;
        currentFile=new File("core/assets/dialogs/"+npcName+".xml");
        conv=new ConversationHandler();
    }

    public void activate(){
        isActive=true;
    }

    public void deactivate(){
        isActive=false;
    }
    public boolean isActive(){
        return isActive;
    }


    public String nextLine (){
        return null;
    }





}