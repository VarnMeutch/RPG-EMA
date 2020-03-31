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
            NodeList tabNoeuds = root.getChildNodes();
            Node n = tabNoeuds.item(1);
            result = n.getNodeName();
            NamedNodeMap att= n.getAttributes();
            result += att.item(0).getNodeValue();





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
