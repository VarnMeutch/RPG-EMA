package com.RPG.game.dialogs;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class XmlReader {

    public static  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder;
    private File xmlFile;

    public XmlReader ( File xmlFile ){
        this.xmlFile = xmlFile;

    }

    public String read() {
        String result = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xml = builder.parse(xmlFile);

            Element root = xml.getDocumentElement();
            Npc npc = new Npc();
            npc.setNpcName(root.getNodeValue());

            NodeList tabNoeuds = root.getChildNodes();
            Line [] lineList = new Line[tabNoeuds.getLength()];

            for (int i = 0; i<tabNoeuds.getLength();i++){

                Node n = tabNoeuds.item(i);
                Node nId = n.getAttributes().getNamedItem("id");
                int id = Integer.parseInt(nId.getNodeValue());
                NodeList tab =n.getChildNodes();

                for (int j=0; j<tab.getLength(); j++ ){

                    Node nText = tab.item(j).getFirstChild();
                    Text text = new Text(nText.getTextContent());

                }


                lineList[i]=new Line(id,);

            }





        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



}
