package com.RPG.game.dialogs;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class XmlReader {


    private DocumentBuilder builder;
    private File xmlFile;

    public XmlReader ( File xmlFile ){
        this.xmlFile = xmlFile;


    }

    public Npc read() {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xml = builder.parse(xmlFile);

            Element root = xml.getDocumentElement();
            Npc npc = new Npc();
            NamedNodeMap attList= root.getAttributes();
            npc.setNpcName(attList.item(0).getNodeValue());

            NodeList tabNoeuds = root.getChildNodes();
            Line [] lineList = new Line[tabNoeuds.getLength()];

            for (int i = 0; i<tabNoeuds.getLength();i++){

                Node n = tabNoeuds.item(i);
                Node nId = n.getAttributes().getNamedItem("id");
                int id = Integer.parseInt(nId.getNodeValue());

                NodeList nList = n.getChildNodes();
                ArrayList<Option> optionList = new ArrayList<>();
                ArrayList<Text> textList =new ArrayList<>();

                for (int k =0; k<nList.getLength();k++){

                    Node cuNode = nList.item(k);

                    if(cuNode.getNodeName().equals("text")){
                        String contText = cuNode.getTextContent();
                        Node tmps = cuNode.getAttributes().getNamedItem("name");
                        Text ajt = new Text(contText,tmps.getNodeValue());
                        textList.add(ajt);
                    }

                    if (cuNode.getNodeName().equals("Option")){
                        String contText = cuNode.getTextContent();
                        Node tmps = cuNode.getAttributes().getNamedItem("action");
                        int action = Integer.parseInt(tmps.getNodeValue());
                        Option ajt = new Option(action,contText);
                        optionList.add(ajt);
                    }




                }

                Options opt = new Options(optionList);
                lineList[i]=new Line(id,opt,textList);

            }

            npc.setLineList(lineList);
            return npc;





        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



}
