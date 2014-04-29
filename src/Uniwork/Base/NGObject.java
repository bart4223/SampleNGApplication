package Uniwork.Base;

public class NGObject implements NGQualityOfService {

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

}
