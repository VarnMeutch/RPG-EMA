package com.RPG.game.dialogs;



import java.io.File;

public class ConversationHandler {

    private XmlReader reader;
    private Npc npc;

    public ConversationHandler(File file){
        reader=new XmlReader(file);
        npc=reader.read();

    }

    public void test(){
        System.out.println(npc.getNpcName());
    }


}
