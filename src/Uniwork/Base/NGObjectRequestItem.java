package Uniwork.Base;

import java.util.Iterator;

public class NGObjectRequestItem extends NGObject {

    protected String FObject;
    protected String FMethod;
    protected NGPropertyList FParams;
    protected NGPropertyList FResults;

    public NGObjectRequestItem(String aObject, String aMethod) {
        super();
        FObject = aObject;
        FMethod = aMethod;
        FParams = new NGPropertyList();
        FResults = new NGPropertyList();
    }

    public String getObject() {
        return FObject;
    }

    public String getMethod() {
        return FMethod;
    }

    public void addParam(String aName, Object aValue) {
        FParams.set(aName, aValue);
    }

    public Object getParamValue(String aName) {
        return FParams.get(aName);
    }

    public Object getParamValue(Integer aIndex) throws Exception {
        if (aIndex > FParams.size() - 1) {
            throw new NGInvalidParamException();
        }
        return FParams.get(aIndex);
    }

    public void addResult(String aName, Object aValue) {
        FResults.set(aName, aValue);
    }

    public Object getResultValue(String aName) {
        return FResults.get(aName);
    }

    public Object getResultValue(Integer aIndex) throws Exception {
        if (aIndex > FResults.size() - 1) {
            throw new NGInvalidParamException();
        }
        return FResults.get(aIndex);
    }

    public Object getFirstResult() {
        Object res = null;
        if (FResults.size() > 0) {
            res = FResults.get(0);
        }
        return res;
    }

    public Iterator<NGPropertyItem> getResults() {
        return FResults.getItemsAs();
    }

}
