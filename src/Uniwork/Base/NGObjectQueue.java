package Uniwork.Base;

import java.util.ArrayList;

public class NGObjectQueue extends NGObject {

    protected ArrayList<NGObjectQueueItem> FItems;
    protected String FName;

    public NGObjectQueue(String aName) {
        super();
        FName = aName;
        FItems = new ArrayList<NGObjectQueueItem>();
    }

    public String getName() {
        return FName;
    }

    public void enter(Object aObject) {
        enter("", aObject);
    }

    public void enter(String aName, Object aObject) {
        NGObjectQueueItem item = new NGObjectQueueItem(aName, aObject);
        FItems.add(item);
    }

    public NGObjectQueueItem leaveItem() {
        NGObjectQueueItem item = null;
        if (FItems.size() > 0) {
            item = FItems.get(0);
            FItems.remove(item);
        }
        return item;
    }

    public Object leave() {
        NGObjectQueueItem item = leaveItem();
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

    public NGObjectQueueItem frontItem() {
        NGObjectQueueItem item = null;
        if (FItems.size() > 0) {
            item = FItems.get(0);
        }
        return item;
    }

    public Object front() {
        NGObjectQueueItem item = frontItem();
        if (item != null) {
            return item.getObject();
        }
        return null;
    }

    public NGObjectQueueItem backItem() {
        NGObjectQueueItem item = null;
        if (FItems.size() > 0) {
            item = FItems.get(FItems.size() - 1);
        }
        return item;
    }

    public Object back() {
        NGObjectQueueItem item = backItem();
        if (item != null) {
            return item.getObject();
        }
        return null;
    }

}
