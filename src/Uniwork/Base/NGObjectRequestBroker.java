package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

import java.io.PrintWriter;
import java.io.StringWriter;
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
        writeLog(String.format("<<<ERROR>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aErrorText));
    }

    protected void DoInvoke(NGObjectRequestItem aItem) {
        NGObjectRequestObject oro = getObject(aItem.getObject());
        if (oro != null) {
            try {
                NGObjectRequestMethod orm = oro.getMethod(aItem.getMethod());
                if (orm.IsActive()) {
                    Method method = oro.getObject().getClass().getMethod(orm.getObjectMethod());
                    method.invoke(oro.getObject());
                    writeLog(5, String.format("ORB invoked [%s->%s]", aItem.getObject(), aItem.getMethod()));
                    writeLog(10, String.format("ORB invoked [%s.%s]", oro.getObject().toString(), orm.getObjectMethod()));
                }
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s] with %s. \n Stack Trace is: \n %s", aItem.getObject(), aItem.getMethod(), e.getMessage(), sw.toString()));
            }
        }
        else {
            writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s]", aItem.getObject(), aItem.getMethod()));
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
