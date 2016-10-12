package by.epam.tc.own_parser.xml_struct;


public class XAttribute {
    private StringBuilder name;
    private StringBuilder data;

    public XAttribute() {
        name = new StringBuilder();
        data = new StringBuilder();
    }

    public StringBuilder getName() {
        return name;
    }

    public StringBuilder getData() {
        return data;
    }

    public String getStringName() { return name.toString(); }

    public String getStringData() { return data.toString(); }
}
