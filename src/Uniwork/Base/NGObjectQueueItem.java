package Uniwork.Base;

public class NGObjectQueueItem extends NGObject {

    protected String FName;
    protected Object FObject;

    public NGObjectQueueItem(String aName, Object aObject) {
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
