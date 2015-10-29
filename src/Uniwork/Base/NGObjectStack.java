package Uniwork.Base;

import java.util.ArrayList;

public class NGObjectStack extends NGObject {

    protected ArrayList<NGObjectStackItem> FItems;
    protected String FName;

    public NGObjectStack() {
        this("");
    }

    public NGObjectStack(String aName) {
        super();
        FItems = new ArrayList<NGObjectStackItem>();
        FName = aName;
    }

    public String getName() {
        return FName;
    }

    public void push(Object aObject) {
        push("", aObject);
    }

    public void push(String aName, Object aObject) {
        NGObjectStackItem item = new NGObjectStackItem(aName, aObject);
        FItems.add(item);
    }

    public NGObjectStackItem popItem() {
        NGObjectStackItem item = null;
        if (FItems.size() > 0) {
            item = FItems.get(FItems.size() - 1);
            FItems.remove(item);
        }
        return item;
    }

    public Object pop() {
        NGObjectStackItem item = popItem();
        if (item != null) {
            return item.getObject();
        }
        return null;
    }

    public Boolean isEmpty() {
        return FItems.size() == 0;
    }

    public Integer getSize() {
        return FItems.size();
    }

    public Object Top() {
        return FItems.get(getSize() - 1).getObject();
    }

}
