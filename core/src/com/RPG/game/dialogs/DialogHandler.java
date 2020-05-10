package com.RPG.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import org.w3c.dom.Node;

import java.io.File;

import static com.badlogic.gdx.graphics.Color.BLACK;

public class DialogHandler extends Dialog {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------

    private String npcName;
    private File currentFile;
    Skin Skin;
    private Stage stage;
    static BitmapFont FONT = new BitmapFont();
    Skin skin = new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json"));
    public static Label.LabelStyle LB = new Label.LabelStyle(FONT, BLACK);


    private boolean isActive;
    static ConversationHandler CONV;


    private String currentText;
    private int currentLine = 000;



    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------

    public DialogHandler(String title,Skin skin){
        super(title, skin);
        this.Skin=skin;
        isActive=false;
        currentText="Ceci est un test";
    }


    // --- METHODS -----------------------------------------------------------------------------------------------------

    public void chooseFile (String npcName){
        this.npcName=npcName;
        currentFile=new File("core/assets/Dialogs/"+npcName+".xml");
        CONV=new ConversationHandler(currentFile);
    }


    @Override
    protected void result(Object object) {

        if (object==null){
            this.hide();
        }
        else{

            CONV.makeNextDialog((Integer) object);
            currentLine=(Integer) object;
            DialogHandler diag=new DialogHandler(CONV.getTalkingNpcName(),getSkin());
            stage.addActor(diag);
            diag.settheStage(stage);
            diag.setFillParent(false);
            diag.setPosition(0,0);
            diag.setSize(Gdx.graphics.getWidth(),150);
            diag.text(CONV.getNextText(),LB);
            if (CONV.isEndDiag()){

                diag.key(Input.Keys.ENTER,null);

            }

            else {
                if(CONV.getNextOptionList()!=null){
                    for(Option o : CONV.getNextOptionList()){
                        diag.button(o.getText(),o.getAction(),new TextButton("",skin,"small").getStyle());
                    }
                }
                else{
                    diag.key(Input.Keys.ENTER,currentLine+1);
                }
            }

        }

    }

    public void settheStage(Stage stage){
        this.stage=stage;
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
