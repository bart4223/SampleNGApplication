package Uniwork.Base;

public class NGObject implements QualityOfService {

    public NGObject() {

    }

    @Override
    public void setProperty(java.lang.Object aObject, String aName, java.lang.Object aValue) {
        try {
            aObject.getClass().getField(aName).set(aObject, aValue);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public java.lang.Object getProperty(java.lang.Object aObject, String aName) {
        try {
            return aObject.getClass().getField(aName).get(aObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
