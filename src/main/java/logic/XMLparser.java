package logic;

/*
 * Created by Jonah on 10/2/2015.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static frames.MainFrame.rhymeList;

public class XMLparser
{
    public void parseXML(String tag, File xml)
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(tag); //"result"

            for(int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    String tempRhymes = eElement.getElementsByTagName("rhymes").item(0).getTextContent();

                    String[] rhymes = tempRhymes.split(",");

                    rhymeList.removeAllElements();

                    if(rhymes.length > 100)
                    {
                        for(int i = 0; i < 100; i++)
                        {
                            rhymeList.addElement(rhymes[i]);
                        }
                    }
                    else
                    {
                        for(String rhyme : rhymes)
                        {
                            rhymeList.addElement(rhyme);
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}