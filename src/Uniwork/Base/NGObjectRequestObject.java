package Uniwork.Base;

import Uniwork.Misc.NGStrings;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGObjectRequestObject extends NGObject {

    protected String FName;
    protected String FAlias;
    protected CopyOnWriteArrayList<NGObjectRequestMethod> FMethods;
    protected Object FObject;

    public NGObjectRequestObject(String aName, Object aObject) {
        this(aName, aObject, "");
    }

    public NGObjectRequestObject(String aName, Object aObject, String aAlias) {
        super();
        FName = aName;
        FObject = aObject;
        FMethods = new CopyOnWriteArrayList<NGObjectRequestMethod>();
        FAlias = aAlias;
    }

    public NGObjectRequestMethod addMethod(String aName, String aObjectMethod) {
        return this.addMethod(aName, aObjectMethod, "");
    }

    public NGObjectRequestMethod addMethod(String aName, String aObjectMethod, String aDescription) {
        NGObjectRequestMethod method = new NGObjectRequestMethod(aName, aObjectMethod, aDescription);
        addMethod(method);
        return method;
    }

    public void addMethod(NGObjectRequestMethod aMethod) {
        FMethods.add(aMethod);
    }

    public String getName() {
        return FName;
    }

    public String getAlias() {
        return FAlias;
    }

    public void setAlias(String aAlias) {
        FAlias = aAlias;
    }

    public Boolean IsThis(String aName) {
        return getName().toUpperCase().equals(aName.toUpperCase()) || getAlias().toUpperCase().equals(aName.toUpperCase());
    }

    public Object getObject() {
        return FObject;
    }

    public NGObjectRequestMethod getMethod(String aName) {
        String name = aName.toUpperCase();
        for (NGObjectRequestMethod method : FMethods) {
            if (method.getName().toUpperCase().equals(name) || method.getAlias().toUpperCase().equals(aName.toUpperCase())) {
                return method;
            }
        }
        return null;
    }

    public String toString() {
        String res = "";
        for (NGObjectRequestMethod method : FMethods) {
            res = NGStrings.addString(res, String.format("%s.%s", FName, method.toString()), ", ");
        }
        return res;
    }

    public Iterator<NGObjectRequestMethod> getMethods() {
        return FMethods.iterator();
    }

}
