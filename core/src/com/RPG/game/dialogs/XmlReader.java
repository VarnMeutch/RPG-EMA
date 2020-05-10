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
            // On build l'outil pour lire les fichiers xml.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xml = builder.parse(xmlFile);

            //On se met à la racine du document et on récupère le nom du npc.
            Element root = xml.getDocumentElement();
            Npc npc = new Npc();
            NamedNodeMap attList= root.getAttributes();
            npc.setNpcName(attList.item(0).getNodeValue());


            // On va regarder parmi les enfants du noeuds Racine pour trouver et construir tous les noeuds Line.
            NodeList tabNoeuds = root.getChildNodes();
            Line [] lineList = new Line[tabNoeuds.getLength()];

            for (int i = 0; i<tabNoeuds.getLength();i++){

                Node n = tabNoeuds.item(i);
                Node nId = n.getAttributes().getNamedItem("id");
                Node endValue = n.getAttributes().getNamedItem("finale");
                int id = Integer.parseInt(nId.getNodeValue()); // On récupère L'id de la ligne.
                boolean endLine = Boolean.parseBoolean(endValue.getNodeValue());


                NodeList nList = n.getChildNodes();
                ArrayList<Option> optionList = new ArrayList<>();
                ArrayList<Text> textList =new ArrayList<>();

                //Pour une Ligne on récupère les objets Texte et les objets Options
                for (int k =0; k<nList.getLength();k++){

                    Node cuNode = nList.item(k);

                    if(cuNode.getNodeName().equals("text")){
                        String contText = cuNode.getTextContent();
                        Node tmps = cuNode.getAttributes().getNamedItem("name");
                        Text ajt = new Text(contText,tmps.getNodeValue());
                        textList.add(ajt);
                    }

                    if(cuNode.getNodeName().equals("options")){
                        NodeList optionNodeList = cuNode.getChildNodes();
                        for(int j=0; j<optionNodeList.getLength();j++){
                            Node cuOptionNode=optionNodeList.item(j);
                            String contText = cuOptionNode.getTextContent();
                            Node tmps = cuOptionNode.getAttributes().getNamedItem("action");
                            int action = Integer.parseInt(tmps.getNodeValue());
                            Option ajt = new Option(action,contText);
                            optionList.add(ajt);
                        }
                    }



                }

                Options opt = new Options(optionList);
                lineList[i]=new Line(id,opt,textList,endLine);

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
