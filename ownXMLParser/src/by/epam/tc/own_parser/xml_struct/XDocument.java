package by.epam.tc.own_parser.xml_struct;

public class XDocument extends XNode {

    private static final String VERSION = "version";
    private static final String ENCODING = "encoding";
    private static final String DEFAULT = "default";

    public XNode getFirstChild() {
        if (children.size() > 0) {
            return children.get(0);
        }
        return null;
    }

    public double getVersion() {
        for (XAttribute temp : attributes) {
            if (temp.getStringName().equals(VERSION)) {
                return Double.parseDouble(temp.getStringData());
            }
        }
        return 0;
    }

    public String getEncoding() {
        for (XAttribute temp : attributes) {
            if (temp.getStringName().equals(ENCODING)) {
                return temp.getStringData();
            }
        }
        return DEFAULT;
    }
}
