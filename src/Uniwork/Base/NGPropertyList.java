package Uniwork.Base;

import java.util.ArrayList;

public class NGPropertyList extends NGObject {

    protected ArrayList<NGPropertyItem> FItems;

    protected void add(NGPropertyItem aItem) {
        FItems.add(aItem);
    }

    protected NGPropertyItem getItem(String aName) {
        for (NGPropertyItem item : FItems) {
            if (item.getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    public NGPropertyList() {
        super();
        FItems = new ArrayList<NGPropertyItem>();
    }

    public NGPropertyItem set(String aName, Object aValue) {
        NGPropertyItem item = getItem(aName);
        if (item != null) {
            item.FValue = aValue;
        } else {
            item = new NGPropertyItem(aName, aValue);
            add(item);
        }
        return item;
    }

    public Object get(String aName) {
        NGPropertyItem item = getItem(aName);
        if (item != null) {
            return item.getValue();
        }
        return null;
    }

    public void clear() {
        FItems.clear();
    }

    public void AssignFrom(ArrayList<NGSerializePropertyItem> aItems) {
        clear();
        for (NGSerializePropertyItem item : aItems) {
            set(item.getName(), item.getValue());
        }
    }

    public void AssignTo(ArrayList<NGSerializePropertyItem> aItems) {
        aItems.clear();
        for (NGPropertyItem item : FItems) {
            NGSerializePropertyItem seritem = new NGSerializePropertyItem();
            seritem.setName(item.getName());
            seritem.setValue(item.getValue());
            aItems.add(seritem);
        }
    }

    public Integer size() {
        return FItems.size();
    }

    public ArrayList<NGPropertyItem> getItems() {
        return FItems;
    }

}
