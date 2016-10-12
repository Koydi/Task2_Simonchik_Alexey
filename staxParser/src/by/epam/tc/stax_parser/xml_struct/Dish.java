package by.epam.tc.stax_parser.xml_struct;

import by.epam.tc.stax_parser.StringFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class Dish extends Printable {

    String name;

    private int[] weight;
    private ArrayList<Options> options;
    private int price = 0;
    private String photo;
    private String description;

    @Override
    public void print(int indent) {
        String indentTag = StringFactory.getString('-', indent * NUMBER_INDENTS, true);
        String indentNestingTag = StringFactory.getString('-', (indent * NUMBER_INDENTS) + NUMBER_INDENTS, true);
        String indentNestingData = StringFactory.getString('=', (indent * NUMBER_INDENTS) + NUMBER_INDENTS, true);
        System.out.println(indentTag + "dish name = " + name);
        System.out.println(indentNestingTag + "description");
        System.out.println(indentNestingData + description);
        if (price != 0) {
            System.out.println(indentNestingTag + "price");
            System.out.println(indentNestingData + price);
        }
        System.out.println(indentNestingTag + "photo");
        System.out.println(indentNestingData + photo);
        System.out.println(indentNestingTag + "weight");
        System.out.println(indentNestingData + Arrays.toString(weight));
        for (Options temp : options) {
            temp.print(indent++);
        }
    }

    public Dish() {
        price = 0;
        options = new ArrayList(0);
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public void setDescription(String description) {
        if (!description.isEmpty()) {
            this.description = description;
        }
    }

    public Options addOptions() {
        Options temp = new Options();
        options.add(temp);
        return temp;
    }

    public void setPrice(String price) {
        if (!price.isEmpty())
            this.price = Integer.parseInt(price);
    }

    public void setPhoto (String photo) {
        if (!photo.isEmpty())
            this.photo = photo;
    }

    public Options getLast() {
        return options.get(options.size() - 1);
    }

    public void setWeight(String weights) throws NumberFormatException {
        if (!weights.isEmpty()) {
            String[] arrayWeights = weights.split(" ");
            int length = arrayWeights.length;
            weight = new int[length];
            for (int i = 0; i < length; i++) {
                weight[i] = Integer.parseInt(arrayWeights[i]);
            }
        }
    }
}
