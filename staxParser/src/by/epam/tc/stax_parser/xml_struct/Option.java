package by.epam.tc.stax_parser.xml_struct;

import by.epam.tc.stax_parser.StringFactory;

public class Option extends Printable {
    private String description;
    private int price;

    public Option() {
        description = null;
    }

    @Override
    public void print(int indent) {
        String indentTag = StringFactory.getString('-', indent * NUMBER_INDENTS, true);
        String indentNestingTag = StringFactory.getString('-', (indent * NUMBER_INDENTS) + NUMBER_INDENTS, true);
        String indentNestingData = StringFactory.getString('=', (indent * NUMBER_INDENTS) + NUMBER_INDENTS, true);
        System.out.println(indentTag + "option");
        System.out.println(indentNestingTag + "description");
        System.out.println(indentNestingData + description);
        System.out.println(indentNestingTag + "price");
        System.out.println(indentNestingData + price);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        if (!price.isEmpty())
            this.price = Integer.parseInt(price);
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}