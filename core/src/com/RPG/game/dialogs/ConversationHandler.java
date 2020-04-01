package com.RPG.game.dialogs;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class ConversationHandler {
    NodeList nodeList;
    Node currentNode;
    XmlReader reader;

    public ConversationHandler(File file){
        reader=new XmlReader(file);


    }

}
