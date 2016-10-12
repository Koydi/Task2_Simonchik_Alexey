package by.epam.tc.own_parser.xml_struct;

import java.util.ArrayList;

public class XNode {
    private XNode parent;
    protected ArrayList<XAttribute> attributes;
    protected ArrayList<XNode> children;
    private StringBuilder name;
    private StringBuilder data;



    public XNode() {
        attributes = new ArrayList(0);
        children = new ArrayList(0);
        name = new StringBuilder();
        data = new StringBuilder();
    }

    public StringBuilder getData() {
        return data;
    }

    public StringBuilder getName() {
        return name;
    }

    public String getStringName() { return name.toString().trim(); }

    public String getStringData() { return data.toString().trim(); }

    public void setParent(XNode parent) {
        this.parent = parent;
    }

    public XAttribute getLastAttribute() {
        return attributes.get(attributes.size() - 1);
    }

    public void addChild(XNode child) {
        children.add(child);
    }

    public void addAttribute(XAttribute attribute) {
        attributes.add(attribute);
    }

    public ArrayList<XNode> getChildren() { return children; }

    public ArrayList<XAttribute> getAttributes() { return attributes; }

}
