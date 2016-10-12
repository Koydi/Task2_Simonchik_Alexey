package by.epam.tc.sax_parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ParserSAX extends DefaultHandler {

    private static SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    private SAXParser saxParser = null;
    private File xmlFile = null;
    private int currentNesting = 0;

    public ParserSAX(String filePath) throws ParserConfigurationException, SAXException {
        xmlFile = new File(filePath);
        saxParser = saxParserFactory.newSAXParser();
    }

    public void parse() throws IOException, SAXException {
        saxParser.parse(xmlFile, this);
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String combinationName, Attributes attributes) throws SAXException {
        OutputHandler.printNode(combinationName, currentNesting);
        OutputHandler.printAttributes(attributes);
        currentNesting++;
    }

    @Override
    public void characters(char[] data, int start, int length) throws SAXException {
        currentNesting--;
        OutputHandler.printNodeValue(data, start, length, currentNesting);
        currentNesting++;
    }

    @Override
    public void endElement(String namespaceURI, String localName,
                           String combinationName) throws SAXException {
        currentNesting--;
    }

    @Override
    public void endDocument() {

    }
}
