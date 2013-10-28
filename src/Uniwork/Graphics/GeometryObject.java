package Uniwork.Graphics;

public class GeometryObject {

    protected Integer FID;
    protected String FName;
    protected String FDescription;
    protected Integer FDimensions;

    protected void InternalUpdate() {

    }

    public GeometryObject() {
        FName = "";
        FDescription = "";
        FDimensions = 0;
        FID = 0;
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

    public void setID(Integer aID) {
        FID = aID;
    }

    public Integer getID( ) {
        return FID;
    }

    public void setDescription(String aDescription) {
        FDescription = aDescription;
    }

    public String getDescription( ) {
        return FDescription;
    }

}
