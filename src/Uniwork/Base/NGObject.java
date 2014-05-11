package Uniwork.Base;

public abstract class NGObject implements NGQualityOfService, NGObjectResolver {

    protected Object DoResolveObject(String aName, Class aClass) {
        if (aName.length() == 0 && aClass.isAssignableFrom(this.getClass())) {
            return this;
        }
        return null;
    }

    public NGObject() {

    }

    @Override
    public void setProperty(Object aObject, String aName, Object aValue) {
        try {
            aObject.getClass().getField(aName).set(aObject, aValue);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        try {
            return aObject.getClass().getField(aName).get(aObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object ResolveObject(Class aClass) {
        return ResolveObject("", aClass);
    }

    @Override
    public Object ResolveObject(String aName, Class aClass) {
        return DoResolveObject(aName, aClass);
    }

}
