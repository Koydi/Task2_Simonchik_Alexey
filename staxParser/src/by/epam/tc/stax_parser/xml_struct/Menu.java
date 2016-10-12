package by.epam.tc.stax_parser.xml_struct;

import by.epam.tc.stax_parser.StringFactory;

import java.util.ArrayList;

public class Menu extends Printable {
    private ArrayList<MenuSection> menuSections;

    public Menu() {
        menuSections = new ArrayList(0);
    }

    @Override
    public void print(int indent) {
        String indentTag = StringFactory.getString('-', indent * NUMBER_INDENTS, true);
        System.out.println(indentTag + "menu");
        for (MenuSection temp : menuSections) {
            temp.print(indent + 1);
        }
    }

    public MenuSection addMenuSection() {
        MenuSection menuSection = new MenuSection();
        menuSections.add(menuSection);
        return menuSection;
    }

    public MenuSection getLast() {
        return menuSections.get(menuSections.size() - 1);
    }
}
