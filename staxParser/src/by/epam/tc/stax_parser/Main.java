package by.epam.tc.stax_parser;

import by.epam.tc.stax_parser.xml_struct.Menu;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        File file = new File("bin/xmlDocument.xml");
        StaxParser staxParser = new StaxParser();
        Menu menu = null;
        try {
            menu = staxParser.parse(new FileInputStream(file));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        menu.print(0);
    }
}
