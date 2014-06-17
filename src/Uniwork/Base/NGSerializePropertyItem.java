package Uniwork.Base;

public class NGSerializePropertyItem extends NGObject {
    public NGSerializePropertyItem() {super();}
    protected String Name = "";
    protected Object Value = "";
    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setValue(Object value) { Value = value;}
    public Object getValue() { return Value; }
}
