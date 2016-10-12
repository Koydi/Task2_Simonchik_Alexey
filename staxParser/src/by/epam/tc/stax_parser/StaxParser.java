package by.epam.tc.stax_parser;

import by.epam.tc.stax_parser.xml_struct.Dish;
import by.epam.tc.stax_parser.xml_struct.Menu;
import by.epam.tc.stax_parser.xml_struct.MenuSection;
import by.epam.tc.stax_parser.xml_struct.Options;
import by.epam.tc.stax_parser.xml_struct.Option;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import java.io.InputStream;

public class StaxParser {
    private static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private XMLStreamReader xmlStreamReader = null;

    public Menu parse(InputStream inputStream) throws XMLStreamException {
        try {
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            return parseXML();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (xmlStreamReader != null)
                xmlStreamReader.close();
        }
        return null;
    }

    private Menu parseXML() throws XMLStreamException {
        Menu root = new Menu();
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch(eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String localName = xmlStreamReader.getLocalName();
                    if (localName.equals("menu")) {
                        parseMenu(root);
                    }
                    break;
                case XMLStreamReader.END_DOCUMENT:
                    break;
            }
        }
        return root;
    }

    private void parseMenu(Menu menu) throws XMLStreamException {
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String localName = xmlStreamReader.getLocalName();
                    if (localName.equals("menu-section")) {
                        MenuSection menuSection = menu.addMenuSection();
                        parseMenuSection(menuSection);
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xmlStreamReader.getLocalName().equals("menu")) {
                        return;
                    }
                    break;
            }
        }
    }

    private void parseMenuSection(MenuSection menuSection) throws XMLStreamException {
        menuSection.setName(xmlStreamReader.getAttributeValue(0));
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String localName = xmlStreamReader.getLocalName();
                    if (localName.equals("dish")) {
                        Dish dish = menuSection.addDish();
                        parseDish(dish);
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xmlStreamReader.getLocalName().equals("menu-section")) {
                        return;
                    }
                    break;
            }
        }
    }

    private void parseDish(Dish dish) throws XMLStreamException {
        dish.setName(xmlStreamReader.getAttributeValue(0));
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String localName = xmlStreamReader.getLocalName();
                    switch (localName) {
                        case "options":
                            Options options = dish.addOptions();
                            parseOptions(options);
                            break;
                        case "weight":
                            dish.setWeight(getElementData());
                            break;
                        case "photo":
                            dish.setPhoto(getElementData());
                            break;
                        case "price":
                            dish.setPrice(getElementData());
                            break;
                        case "description":
                            dish.setDescription(getElementData());
                            break;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xmlStreamReader.getLocalName().equals("dish")) {
                        return;
                    }
                    break;
            }
        }
    }

    private void parseOptions(Options options) throws XMLStreamException {
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String localName = xmlStreamReader.getLocalName();
                    if (localName.equals("option")) {
                        Option option = options.addOption();
                        parseOption(option);
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xmlStreamReader.getLocalName().equals("options")) {
                        return;
                    }
                    break;
            }
        }
    }

    private void parseOption(Option option) throws XMLStreamException {
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String localName = xmlStreamReader.getLocalName();
                    switch(localName) {
                        case "description":
                            option.setDescription(getElementData());
                            break;
                        case "price":
                            option.setPrice(getElementData());
                            break;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xmlStreamReader.getLocalName().equals("option")) {
                        return;
                    }
                    break;
            }
        }
    }

    private String getElementData() throws XMLStreamException {
        StringBuilder stringBuilder = new StringBuilder();
        while (xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamReader.CHARACTERS:
                    stringBuilder.append(xmlStreamReader.getText());
                    break;
                case XMLStreamReader.CDATA:
                    stringBuilder.append(xmlStreamReader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return stringBuilder.toString().trim();
            }
        }
        return stringBuilder.toString().trim();
    }
}
