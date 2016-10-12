package by.epam.tc.own_parser.parser;

import by.epam.tc.own_parser.xml_struct.XAttribute;
import by.epam.tc.own_parser.xml_struct.XDocument;
import by.epam.tc.own_parser.xml_struct.XNode;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Parser {
    private XDocument root;
    private Stack<XNode> stack;
    private int currentState;
    private InputStreamReader fileInputStream;

    public Parser() {
        stack = new Stack();
        root = null;
        fileInputStream = null;
    }

    public XDocument startParse(File file) throws IOException {
        fileInputStream = new InputStreamReader(new FileInputStream(file), "UTF8");
        root = new XDocument();
        stack.push(root);
        currentState = 0;
        parse();
        return root;
    }

    public void parse() throws IOException {
        int currentChar;
        while ((currentChar = fileInputStream.read()) > -1) {
            changeState(currentChar);
            stateHandler(currentChar);
        }
    }



    private void changeState(int currentChar) {
        int literalNumber = TransitionTable.getLiteralNumber(currentChar);
        int newState = newState(literalNumber);
        if (newState >= 0) {
            currentState = newState;
            while (TransitionTable.isUnconditional(currentState)) {
                currentState = newState(TransitionTable.UNCONDITIONAL_STATE);
            }
        }
    }


    private int newState(int literalNumber) {
        int newState = TransitionTable.getNextState(currentState, literalNumber);
        if (newState != currentState) {
            onEndState(currentState);
            onBeginState(newState);
        }
        return newState;
    }


    private void onBeginState(int newState) {
        switch(newState) {
            case TransitionTable.ELEMENT_NAME_STATE:
                XNode newNode = new XNode();
                XNode currentNode = stack.peek();
                newNode.setParent(currentNode);
                currentNode.addChild(newNode);
                stack.push(newNode);
                break;

            case TransitionTable.ATTRIBUTE_NAME_STATE:
                XNode node = stack.peek();
                XAttribute newAttribute = new XAttribute();
                node.addAttribute(newAttribute);
                break;
        }
    }

    private void onEndState(int oldState) {
        switch(oldState) {
            case TransitionTable.END_WRITING_ELEMENT:
                stack.pop();
                break;
            case TransitionTable.CLOSE_TAG:
                stack.pop();
                break;
        }
    }

    private void stateHandler(int currentChar) {
        StringBuilder currentBuffer;
        switch(currentState) {
            case TransitionTable.ATTRIBUTE_NAME_STATE:
                currentBuffer = stack.peek().getLastAttribute().getName();
                currentBuffer.append((char)currentChar);
                break;
            case TransitionTable.ATTRIBUTE_VALUE_STATE:
                currentBuffer = stack.peek().getLastAttribute().getData();
                currentBuffer.append((char)currentChar);
                break;
            case TransitionTable.ELEMENT_NAME_STATE:
                currentBuffer = stack.peek().getName();
                currentBuffer.append((char)currentChar);
                break;
            case TransitionTable.ELEMENT_DATA_STATE:
                currentBuffer = stack.peek().getData();
                currentBuffer.append((char)currentChar);
                break;
        }
    }
}
