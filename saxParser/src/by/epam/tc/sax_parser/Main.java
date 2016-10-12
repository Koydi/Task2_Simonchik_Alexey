package by.epam.tc.sax_parser;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ParserSAX parserSAX;
        try {
            parserSAX = new ParserSAX("bin/xmlDocument.xml");
            parserSAX.parse();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
