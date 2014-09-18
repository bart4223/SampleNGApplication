package Uniwork.Base;

import Uniwork.Base.NGObject;

public class NGObjectStackItem extends NGObject {

    protected String FName;
    protected Object FObject;

    public NGObjectStackItem(String aName, Object aObject) {
        super();
        FName = aName;
        FObject = aObject;
    }

    public String getName() {
        return FName;
    }

    public Object getObject() {
        return FObject;
    }

}
