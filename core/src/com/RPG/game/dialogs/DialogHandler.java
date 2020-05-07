package com.RPG.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.w3c.dom.Node;

import java.io.File;

import static com.badlogic.gdx.graphics.Color.BLACK;

public class DialogHandler extends Dialog {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------

    private String npcName;
    private File currentFile;
     Skin Skin;
    private Stage stage;
    Label.LabelStyle lb;


    private boolean isActive;
    private ConversationHandler conv;
    private String currentText;



    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public DialogHandler(Skin skin){
        super("Dialogue", skin);
        this.Skin=skin;
        isActive=false;
        currentText="Ceci est un test";
    }


    // --- METHODS -----------------------------------------------------------------------------------------------------

    public void chooseFile (String npcName){
        this.npcName=npcName;
        currentFile=new File("core/assets/Dialogs/"+npcName+".xml");
        conv=new ConversationHandler(currentFile);
    }

    public void setLb(Label.LabelStyle lb) {
        this.lb = lb;
    }

    @Override
    protected void result(Object object) {

        System.out.println(object.toString());
        DialogHandler diag=new DialogHandler(getSkin());
        stage.addActor(diag);
        diag.settheStage(stage);
        diag.setFillParent(false);
        diag.setPosition(0,0);
        diag.setSize(Gdx.graphics.getWidth(),150);
        diag.text("next",lb);
        diag.key(Input.Keys.ENTER, "next");


    }

    public void settheStage(Stage stage){
        this.stage=stage;
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
