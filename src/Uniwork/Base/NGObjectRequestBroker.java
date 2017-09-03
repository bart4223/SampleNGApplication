package Uniwork.Base;

import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGStrings;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGObjectRequestBroker extends NGObject {

    protected Object FOwner;
    protected CopyOnWriteArrayList<NGObjectRequestObject> FObjects;
    protected NGLogManager FLogManager;

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    protected void writeError(String aText) {
        writeError(0, aText);
    }

    protected void writeError(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, NGLogEntry.LogType.Error);
        }
    }

    protected void writeError(String aMethodName, String aErrorText) {
        if (FLogManager != null) {
            String text = String.format("<<<ERROR>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aErrorText);
            writeError(text);
        }
    }

    protected void DoInvoke(NGObjectRequestItem aItem) {
        Boolean invoked = false;
        Boolean hadError = false;
        for (NGObjectRequestObject oro : FObjects) {
            if (oro.getName().toUpperCase().equals(aItem.getObject().toUpperCase())) {
                try {
                    NGObjectRequestMethod orm = oro.getMethod(aItem.getMethod());
                    if (orm != null && orm.IsActive()) {
                        Method method = null;
                        Object res = null;
                        switch (orm.getParamCount()) {
                            case 0:
                                method = oro.getObject().getClass().getMethod(orm.getObjectMethod());
                                res = method.invoke(oro.getObject());
                                invoked = true;
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
                                    case Object:
                                        method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), Object.class);
                                        break;
                                }
                                if (method != null) {
                                    Object param = aItem.getParamValue(0);
                                    switch (orm.getParamKind(0)) {
                                        case String:
                                            if (param == null) {
                                                param = "";
                                            }
                                            break;
                                        case Double:
                                            if (param == null) {
                                                param = 0.0;
                                            } else if (param instanceof String) {
                                                param = Double.parseDouble((String)param);
                                            }
                                            break;
                                        case Integer:
                                            if (param == null) {
                                                param = 0;
                                            } else if (param instanceof String) {
                                                param = Integer.parseInt((String)param);
                                            }
                                            break;
                                    }
                                    if (param == null) {
                                        throw new NGInvalidParamException();
                                    }
                                    res = method.invoke(oro.getObject(), param);
                                    invoked = true;
                                } else
                                    writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s] (1)", aItem.getObject(), aItem.getMethod()));
                                break;
                            case 2:
                                switch (orm.getParamKind(0)) {
                                    case Double:
                                        switch (orm.getParamKind(1)) {
                                            case Double:
                                                method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), Double.class, Double.class);
                                                break;
                                            case String:
                                                method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), Double.class, String.class);
                                                break;
                                        }
                                        break;
                                    case String:
                                        switch (orm.getParamKind(1)) {
                                            case Double:
                                                method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), String.class, Double.class);
                                                break;
                                            case String:
                                                method = oro.getObject().getClass().getMethod(orm.getObjectMethod(), String.class, String.class);
                                                break;
                                        }
                                        break;
                                }
                                if (method != null) {
                                    Object[] params = new Object[2];
                                    params[0] = aItem.getParamValue(0);
                                    params[1] = aItem.getParamValue(1);
                                    for (int i = 0; i < 2; i++) {
                                        switch (orm.getParamKind(i)) {
                                            case String:
                                                if (params[i] == null) {
                                                    params[i] = "";
                                                }
                                                break;
                                            case Double:
                                                if (params[i] == null) {
                                                    params[i] = 0.0;
                                                } else if (params[i] instanceof String) {
                                                    params[i] = Double.parseDouble((String)params[i]);
                                                }
                                                break;
                                        }
                                        if (params[i] == null) {
                                            throw new NGInvalidParamException();
                                        }
                                    }
                                    res = method.invoke(oro.getObject(), params[0], params[1]);
                                    invoked = true;
                                } else
                                    writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s] (2)", aItem.getObject(), aItem.getMethod()));
                                break;
                        }
                        if (res != null) {
                            aItem.addResult("Result", res);
                        }
                        writeLog(5, String.format("ORB invoked [%s->%s]", aItem.getObject(), aItem.getMethod()));
                        writeLog(10, String.format("ORB invoked [%s.%s]", oro.getObject().toString(), orm.getObjectMethod()));
                    }
                } catch (Exception e) {
                    hadError = true;
                    if (e instanceof NGCustomScriptException) {
                        writeError(e.getMessage());
                    } else {
                        StringWriter sw = new StringWriter();
                        e.printStackTrace(new PrintWriter(sw));
                        writeError("DoInvoke", String.format("<<<ERROR>>> ORB can't invoke [%s.%s] with %s. \n Stack Trace is: \n %s", aItem.getObject(), aItem.getMethod(), e.getMessage(), sw.toString()));
                    }
                }

            }
        }
        if (!invoked && !hadError) {
            writeError(String.format("Command \"%s\" is unknown!", aItem.getMethod()));
        }
    }

    protected NGObjectRequestObject getObject(String aName) {
        for (NGObjectRequestObject object : FObjects) {
            if (object.getName().toUpperCase().equals(aName.toUpperCase())) {
                return object;
            }
        }
        return null;
    }

    public NGObjectRequestBroker(Object aOwner) {
        super();
        FOwner = aOwner;
        FObjects = new CopyOnWriteArrayList<NGObjectRequestObject>();
        FLogManager = null;
    }

    public Object getOwner() {
        return FOwner;
    }

    public NGObjectRequestObject addObject(String aName, Object aObject) {
        NGObjectRequestObject res = getObject(aName);
        if (res == null || !res.getObject().equals(aObject)) {
            res = new NGObjectRequestObject(aName, aObject);
            addObject(res);
        }
        return res;
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

    public String toString() {
        String res = "";
        for (NGObjectRequestObject obj : FObjects) {
            res = NGStrings.addString(res, obj.toString(), ", ");
        }
        return res;
    }

    public Iterator<NGObjectRequestObject> getObjects() {
        return FObjects.iterator();
    }

}
