package com.RPG.game.dialogs;




import java.io.File;
import java.util.ArrayList;

public class ConversationHandler {

    private XmlReader reader;
    private Npc npc;
    private Line [] convTab;
    private String nextText;
    private ArrayList<Option> nextOptionList;
    private String talkingNpcName;
    private boolean endDiag;

    public String getNextText() {
        return nextText;
    }

    public ArrayList<Option> getNextOptionList() {
        return nextOptionList;
    }

    public String getTalkingNpcName() {
        return talkingNpcName;
    }

    public ConversationHandler(File file){
        reader=new XmlReader(file);
        npc=reader.read();
        convTab = npc.getLineList();

    }

    public void makeNextDialog(int id) {
        Line l = findLine(id);
        endDiag=l.isFinal();
        if(l.hasOptions()){
            nextOptionList=l.getOptions().getOptionList();;
        }
        else{
            nextOptionList=null;
        }

        ArrayList<Text> textList=l.getTexts();
        Text t = textList.get(0);
        nextText=t.getText();
        talkingNpcName=t.getTalkingName();

    }

    public boolean isEndDiag(){
        return endDiag;
    }


    public Line findLine(int id){
        for (Line l:convTab){
            if(l.getId()==id){
                return l;
            }
        }

        return null;

    }





}
