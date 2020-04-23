package com.RPG.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.w3c.dom.Node;

import java.io.File;

public class DialogHandler extends Dialog {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------

    private String npcName;
    private File currentFile;
    private boolean isActive;
    private ConversationHandler conv;
    private String currentText;


    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public DialogHandler(Skin skin){
        super("Dialogue", skin);
        isActive=false;
        currentText="Ceci est un test";
    }



    // --- METHODS -----------------------------------------------------------------------------------------------------

    public void chooseFile (String npcName){
        this.npcName=npcName;
        currentFile=new File("core/assets/Dialogs/"+npcName+".xml");
        conv=new ConversationHandler(currentFile);
    }

    public void test (){
        conv.test();
    }


    public void displayText () {

        text(currentText);
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





}
