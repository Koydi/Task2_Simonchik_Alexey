package by.epam.tc.stax_parser.xml_struct;

import by.epam.tc.stax_parser.StringFactory;

import java.util.ArrayList;

public class MenuSection extends Printable {
    private ArrayList<Dish> dishes;

    private String name;

    public MenuSection() {
        dishes = new ArrayList(0);
    }

    @Override
    public void print(int indent) {
        String indentTag = StringFactory.getString('-', indent * NUMBER_INDENTS, true);
        System.out.println(indentTag + "menu-section name = " + name);
        for (Dish temp : dishes) {
            temp.print(indent + 1);
        }
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public Dish addDish() {
        Dish dish = new Dish();
        dishes.add(dish);
        return dish;
    }
}
