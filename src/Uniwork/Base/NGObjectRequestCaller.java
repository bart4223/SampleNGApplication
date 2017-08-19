package Uniwork.Base;

import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGStrings;

import java.util.Iterator;

public class NGObjectRequestCaller extends NGObject {

    protected NGObjectRequestInvoker FInvoker;
    protected NGLogManager FLogManager;
    protected String FObjectName;
    protected String FObjectMethod;
    protected NGPropertyList FParams;

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

    protected void DoInvoke() {
        NGObjectRequestItem item = new NGObjectRequestItem(FObjectName, FObjectMethod);
        Iterator<NGPropertyItem> itr = FParams.getItemsAs();
        while (itr.hasNext()) {
            NGPropertyItem prop = itr.next();
            item.addParam(prop.getName(), prop.getValue());
        }
        FInvoker.Invoke(item);
    }

    public NGObjectRequestCaller(NGObjectRequestInvoker aInvoker) {
        super();
        FParams = new NGPropertyList();
        FInvoker = aInvoker;
        FLogManager = null;
        FObjectName = "";
        FObjectMethod = "";
    }

    public NGObjectRequestInvoker getInvoker() {
        return FInvoker;
    }

    public void setObjectName(String aName) {
        FObjectName = aName;
    }

    public String getObjectName() {
        return FObjectName;
    }

    public void setObjectMethod(String aMethod) {
        FObjectMethod = aMethod;
    }

    public String getObjectMethod() {
        return FObjectMethod;
    }

    public void Invoke() {
        DoInvoke();
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public Boolean HasInvoker() {
        return getInvoker() != null;
    }

    public void addParam(Object aValue) {
        addParam(FParams.size().toString(), aValue);
    }

    public void addParam(String aName, Object aValue) {
        FParams.add(new NGPropertyItem(aName, aValue));
    }

    @Override
    public String toString() {
        String params = "";
        Iterator<NGPropertyItem> itr = FParams.getItemsAs();
        while (itr.hasNext()) {
            NGPropertyItem param = itr.next();
            Object value = param.getValue();
            if (value == null) {
                value = "";
            }
            params = NGStrings.addString(params, value.toString(), " ");
        }
        return NGStrings.addString(String.format("%s.%s", getObjectName(), getObjectMethod()), params, " ");
    }

}
