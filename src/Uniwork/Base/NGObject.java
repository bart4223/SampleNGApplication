package Uniwork.Base;

public abstract class NGObject implements NGQualityOfService, NGObjectResolver, NGObjectTransformation {

    protected NGObject FResolver;

    protected Object DoResolveObject(String aName, Class aClass) {
        Object obj = this;
        if (aName.length() > 0) {
            obj = getProperty(obj, aName);
        }
        if (obj == null) {
            obj = this;
        }
        if (aClass.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        if (FResolver != null) {
            return FResolver.ResolveObject(aName, aClass);
        }
        return null;
    }

    protected void DoAssignTo(Object aObject) {

    }

    protected void DoAssignFrom(Object aObject) {

    }

    public NGObject() {
        FResolver = null;
    }

    @Override
    public Boolean setProperty(Object aObject, String aName, Object aValue) {
        Boolean res;
        try {
            aObject.getClass().getField(aName).set(aObject, aValue);
            res = true;
        }
        catch (Exception e) {
            res = false;
        }
        return res;
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        try {
            return aObject.getClass().getField(aName).get(aObject);
        }
        catch (Exception e) {
        }
        return null;
    }

    @Override
    public void setProperties(Object aObject, NGPropertyList aProps) {
        for (NGPropertyItem item : aProps.getItems()) {
            setProperty(aObject, item.getName(), item.getValue());
        }
    }

    @Override
    public Object ResolveObject(Class aClass) {
        return ResolveObject("", aClass);
    }

    @Override
    public Object ResolveObject(String aName, Class aClass) {
        return DoResolveObject(aName, aClass);
    }

    @Override
    public void AssignTo(Object aObject) {
        DoAssignTo(aObject);
    }

    @Override
    public void AssignFrom(Object aObject) {
        DoAssignFrom(aObject);
    }

    public NGObject getResolver() {
        return FResolver;
    }

    public void setResolver(NGObject aResolver) {
        FResolver = aResolver;
    }

    public static Object createObjectByName(Object aObject, String aName) {
        Object res = null;
        try {
            res = aObject.getClass().getClassLoader().loadClass(aName).getConstructor().newInstance();
        }
        catch (Exception e){
        }
        return res;
    }

}
