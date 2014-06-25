package Uniwork.Base;

import Uniwork.Misc.NGLogManager;
import java.lang.reflect.Method;

import java.util.ArrayList;

public class NGObjectRequestBroker extends NGObject {

    protected Object FOwner;
    protected ArrayList<NGObjectRequestObject> FObjects;
    protected NGLogManager FLogManager;

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    protected void writeError(String aMethodName, String aErrorText) {
        writeLog(0, String.format("<<<ERROR>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aErrorText));
    }

    protected void DoInvoke(NGObjectRequestItem aItem) {
        NGObjectRequestObject obj = getObject(aItem.getObject());
        try {
            Method method = obj.getObject().getClass().getMethod(aItem.getMethod());
            method.invoke(obj);
        } catch (Exception e) {
            writeError("DoInvoke", e.getMessage());
        }
    }

    protected NGObjectRequestObject getObject(String aName) {
        for (NGObjectRequestObject object : FObjects) {
            if (object.getName().equals(aName)) {
                return object;
            }
        }
        return null;
    }

    public NGObjectRequestBroker(Object aOwner) {
        super();
        FOwner = aOwner;
        FObjects = new ArrayList<NGObjectRequestObject>();
        FLogManager = null;
    }

    public Object getOwner() {
        return FOwner;
    }

    public NGObjectRequestObject addObject(String aName, Object aObject) {
        NGObjectRequestObject object = new NGObjectRequestObject(aName, aObject);
        addObject(object);
        return object;
    }

    public void addObject(NGObjectRequestObject aObject) {
        FObjects.add(aObject);
    }

    public void Invoke(NGObjectRequestItem aItem) {
        DoInvoke(aItem);
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

}
