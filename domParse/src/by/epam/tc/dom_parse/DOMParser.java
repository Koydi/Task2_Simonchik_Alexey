package by.epam.tc.dom_parse;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMParser {
    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    private DocumentBuilder documentBuilder;
    private File xmlFile = null;
    private Document document = null;

    public DOMParser() {
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public DOMParser(String filePath) {
        this();
        xmlFile = new File(filePath);
    }

    public void handle() throws IOException, SAXException {
        if (xmlFile != null) {
            document = documentBuilder.parse(xmlFile);

        }
    }

    public void setFile(File file) {
        xmlFile = file;
    }

    public void setFile(String filepath) {
        xmlFile = new File(filepath);
    }

    public Document getDocument() {
        return document;
    }
}
