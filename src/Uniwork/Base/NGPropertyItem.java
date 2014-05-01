package Uniwork.Base;

public class NGPropertyItem extends NGObject {

    protected String FName;
    protected Object FValue;

    public NGPropertyItem(String aName, Object aValue) {
        super();
        FName = aName;
        FValue = aValue;
    }

    public String getName() {
        return FName;
    }

    public Object getValue() {
        return FValue;
    }

}
