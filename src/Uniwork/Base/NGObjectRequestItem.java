package Uniwork.Base;

public class NGObjectRequestItem extends NGObject {

    protected String FObject;
    protected String FMethod;
    protected NGPropertyList FParams;

    public NGObjectRequestItem(String aObject, String aMethod) {
        super();
        FObject = aObject;
        FMethod = aMethod;
        FParams = new NGPropertyList();
    }

    public String getObject() {
        return FObject;
    }

    public String getMethod() {
        return FMethod;
    }

    public void addParam(String aName, Object aValue) {
        FParams.set(aName, aValue);
    }

    public Object getParamValue(String aName) {
        return FParams.get(aName);
    }

    public Object getParamValue(Integer aIndex) {
        return FParams.get(aIndex);
    }

}
