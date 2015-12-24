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
        for (NGObjectRequestObject oro : FObjects) {
            try {
                NGObjectRequestMethod orm = oro.getMethod(aItem.getMethod());
                if (orm != null && orm.IsActive()) {
                    Method method = null;
                    switch (orm.getParamCount()) {
                        case 0:
                            method = oro.getObject().getClass().getMethod(orm.getObjectMethod());
                            method.invoke(oro.getObject());
                            break;
                        case 1:
                            switch (orm.getParamKind(0)) {
                                case Integer:
                                    method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), Integer.class);
                                    break;
                                case Double:
                                    method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), Double.class);
                                    break;
                                case String:
                                    method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), String.class);
                                    break;
                            }
                            if (method != null)
                                method.invoke(oro.getObject(), aItem.getParamValue(0));
                            else
                                writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s] (1)", aItem.getObject(), aItem.getMethod()));
                            break;
                        case 2:
                            switch (orm.getParamKind(0)) {
                                case Double:
                                    switch (orm.getParamKind(1)) {
                                        case Double:
                                            method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), Double.class, Double.class);
                                            break;
                                    }
                                    break;
                            }
                            if (method != null)
                                method.invoke(oro.getObject(), aItem.getParamValue(0), aItem.getParamValue(1));
                            else
                                writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s] (2)", aItem.getObject(), aItem.getMethod()));
                            break;
                    }
                    writeLog(5, String.format("ORB invoked [%s->%s]", aItem.getObject(), aItem.getMethod()));
                    writeLog(10, String.format("ORB invoked [%s.%s]", oro.getObject().toString(), orm.getObjectMethod()));
                }
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s] with %s. \n Stack Trace is: \n %s", aItem.getObject(), aItem.getMethod(), e.getMessage(), sw.toString()));
            }
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
        NGObjectRequestObject object =  getObject(aName);
        object = new NGObjectRequestObject(aName, aObject);
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
