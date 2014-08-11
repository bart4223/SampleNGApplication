package Uniwork.Base;

public class NGObjectRequestCaller extends NGObject {

    protected NGObjectRequestInvoker FInvoker;
    protected String FObjectName;
    protected String FObjectMethod;

    protected void DoInvoke() {
        NGObjectRequestItem item = new NGObjectRequestItem(FObjectName, FObjectMethod);
        FInvoker.Invoke(item);
    }

    public NGObjectRequestCaller(NGObjectRequestInvoker aInvoker) {
        super();
        FInvoker = aInvoker;
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

}
