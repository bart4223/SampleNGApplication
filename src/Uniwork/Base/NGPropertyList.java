package Uniwork.Base;

import Uniwork.Misc.NGStrings;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGPropertyList extends NGObject {

    protected CopyOnWriteArrayList<NGPropertyItem> FItems;

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

    @Override
    protected void DoAssignFrom(Object aObject) {
        clear();
        if (aObject instanceof NGPropertyList) {
            NGPropertyList src = (NGPropertyList)aObject;
            for (NGPropertyItem item : src.getItems()) {
                set(item.getName(), item.getValue());
            }
        } else if (aObject instanceof CopyOnWriteArrayList<?>) {
            CopyOnWriteArrayList<?> items = (CopyOnWriteArrayList<?>)aObject;
            for (Object item : items) {
                if (item instanceof NGSerializePropertyItem) {
                    NGSerializePropertyItem propitem = (NGSerializePropertyItem)item;
                    set(propitem.getName(), propitem.getValue());
                }
            }
        }
    }

    @Override
    protected void DoAssignTo(Object aObject) {
        if (aObject instanceof NGPropertyList) {
            NGPropertyList trg = (NGPropertyList)aObject;
            trg.clear();
            for (NGPropertyItem item : FItems) {
                trg.set(item.getName(), item.getValue());
            }
        }
    }

    public NGPropertyList() {
        super();
        FItems = new CopyOnWriteArrayList<NGPropertyItem>();
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

    public Object get(Integer aIndex) {
        NGPropertyItem item = FItems.get(aIndex);
        return item.getValue();
    }

    public void clear() {
        FItems.clear();
    }

    public void AssignTo(CopyOnWriteArrayList<NGSerializePropertyItem> aItems) {
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

    public CopyOnWriteArrayList<NGPropertyItem> getItems() {
        return FItems;
    }

    public Iterator<NGPropertyItem> getItemsAs() {
        return FItems.iterator();
    }

    @Override
    public String toString() {
        String res = "";
        Iterator<NGPropertyItem> itr = getItemsAs();
        while (itr.hasNext()) {
            NGPropertyItem prop = itr.next();
            Object value = prop.getValue();
            if (value != null) {
                value = value.toString();
            }
            res = NGStrings.addString(res, String.format("%s=%s", prop.getName(), value), ";");
        }
        return res;
    }

}
