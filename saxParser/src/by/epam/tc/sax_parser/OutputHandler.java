package by.epam.tc.sax_parser;

import org.xml.sax.Attributes;

public class OutputHandler {
    private static final int NUMBER_INDENT = 2;

    public static void printNode(String nameNode, int currentNesting) {
        String indent = StringFactory.getString('-', currentNesting * NUMBER_INDENT, true);
        System.out.print('\n' + indent + nameNode);
    }

    public static void printNodeValue(char[] data, int start, int length, int currentNesting) {
        String indent = StringFactory.getString('=', currentNesting * NUMBER_INDENT, true);
        String dataString = new String(data, start, length).trim();
        if (!dataString.isEmpty())
            System.out.print('\n' + indent + dataString);
    }

    public static void printAttributes(Attributes attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            System.out.print(' ' + attributes.getQName(i) + "=" + attributes.getValue(i));
        }
    }
}
