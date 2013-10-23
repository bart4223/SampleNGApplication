package Uniwork.Graphics;

public class GeometryObject {

    protected String FName;
    protected String FDescription;
    protected Integer FDimensions;

    public GeometryObject() {
        FName = "";
        FDescription = "";
        FDimensions = 0;
    }

    public GeometryObject(String aName, String aDescription) {
        FName = aName;
        FDescription = aDescription;
    }

    public void setName(String aName) {
        FName = aName;
    }

    public String getName( ) {
        return FName;
    }

    public void setDescription(String aDescription) {
        FDescription = aDescription;
    }

    public String getDescription( ) {
        return FDescription;
    }

}
