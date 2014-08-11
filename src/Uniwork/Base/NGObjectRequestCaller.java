package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

public class NGObjectRequestCaller extends NGObject {

    protected NGObjectRequestInvoker FInvoker;
    protected NGLogManager FLogManager;
    protected String FObjectName;
    protected String FObjectMethod;

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
        FInvoker.Invoke(item);
    }

    public NGObjectRequestCaller(NGObjectRequestInvoker aInvoker) {
        super();
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

}
