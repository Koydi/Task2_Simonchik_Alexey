package by.epam.tc.dom_parse;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DOMParser domParser =  new DOMParser();
        domParser.setFile(new File("bin/xmlDocument.xml"));
        try {
            domParser.handle();

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        OutputHandler.printInformation(domParser.getDocument());
    }
}
