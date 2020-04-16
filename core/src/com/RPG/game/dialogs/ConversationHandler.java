package com.RPG.game.dialogs;



import java.io.File;
import java.util.ArrayList;

public class ConversationHandler {

    private XmlReader reader;
    private Npc npc;
    private Line [] convTab;


    public ConversationHandler(File file){
        reader=new XmlReader(file);
        npc=reader.read();
        convTab = npc.getLineList();

    }

    public void test(){
        System.out.println(npc.getNpcName());
        System.out.println("--------------");
        Line tmp = convTab[0];
        System.out.println(tmp.getId());
        ArrayList<Text> textList = tmp.getTexts();
        for(Text t:textList){
            System.out.println(t.getTalkingName() + ": " + t.getText());
        }


    }


}
