import by.epam.tc.own_parser.parser.Parser;
import by.epam.tc.own_parser.xml_struct.XAttribute;
import by.epam.tc.own_parser.xml_struct.XDocument;
import by.epam.tc.own_parser.xml_struct.XNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static int nesting = 0;

    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            XDocument document = parser.startParse(new File("bin/xmlDocument.xml"));
            System.out.println(document.getEncoding());
            System.out.println(document.getVersion());
            print(document.getFirstChild());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void print (XNode node) {
        nesting++;
        ArrayList<XNode> children = node.getChildren();
        String indent = StringFactory.getString('-', nesting, true);
        System.out.println('\n' + indent + node.getStringName());
        printAttributes(node.getAttributes());
        printData(node.getStringData());
        children.forEach(Main::print);
        nesting--;
    }

    private static void printAttributes(ArrayList<XAttribute> attributes) {
        for (XAttribute temp : attributes) {
            System.out.print(" " + temp.getStringName() +  " = " + temp.getStringData());
        }
    }

    private static void printData(String data) {
        if (!data.isEmpty()) {
            String indent = StringFactory.getString('=', nesting, true);
            System.out.println(indent + data);
        }
    }
}
