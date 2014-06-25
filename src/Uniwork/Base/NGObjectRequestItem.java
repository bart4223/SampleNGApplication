package Uniwork.Base;

public class NGObjectRequestItem extends NGObject {

    protected String FObject;
    protected String FMethod;

    public NGObjectRequestItem(String aObject, String aMethod) {
        super();
        FObject = aObject;
        FMethod = aMethod;
    }

    public String getObject() {
        return FObject;
    }

    public String getMethod() {
        return FMethod;
    }

}
