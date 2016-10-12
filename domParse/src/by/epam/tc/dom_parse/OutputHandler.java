package by.epam.tc.dom_parse;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class OutputHandler {

    private static final int NUMBER_INDENTS = 2;

    public static void printInformation(Document document) {
        Node root;
        if (document != null) {
            root = document.getDocumentElement();
        } else {
            return;
        }
        StringFactory.setStartSymbol('|');
        printNodeInformation(root, 0);
    }

    private static void printNodeInformation(Node node, int indentsCount) {
        String nodeName = node.getNodeName();
        if (nodeName.equals("#text")) {
            String nodeValue = node.getNodeValue().trim();
            if (!nodeValue.isEmpty()) {
                int temp = indentsCount - NUMBER_INDENTS;
                String indent = StringFactory.getString('=', temp, true);
                System.out.print('\n' + indent + nodeValue);
            }
        } else {
            String indent = StringFactory.getString('-', indentsCount, true);
            indentsCount += NUMBER_INDENTS;
            System.out.print("\n" + indent + nodeName);
            printAttributes(node);
        }
        NodeList children = node.getChildNodes();
        int numberChildrenNodes = children.getLength();
        for (int i = 0; i < numberChildrenNodes; i++) {
            Node currentNode = children.item(i);
            printNodeInformation(currentNode, indentsCount);
        }
    }

    private static void printAttributes(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Node attribute = attributes.item(i);
            System.out.print(" " + attribute.getNodeName() + "=" + attribute.getNodeValue());
        }
    }
}
