package by.epam.tc.stax_parser.xml_struct;

import by.epam.tc.stax_parser.StringFactory;

import java.util.ArrayList;

public class Options extends Printable {

    private ArrayList<Option> options;

    public Options() {
        options = new ArrayList(0);
    }

    @Override
    public void print(int indent) {
        String indentTag = StringFactory.getString('-', indent * NUMBER_INDENTS, true);
        System.out.println(indentTag + "option");
        for (Option temp : options) {
            temp.print(indent + 1);
        }
    }

    public Option addOption() {
        Option option = new Option();
        options.add(option);
        return option;
    }
}

