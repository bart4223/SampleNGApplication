package Uniwork.Graphics;

public class GeometryObject {

    protected Integer FDimensions;
    protected String FID;

    protected void InternalUpdate() {
        // NOP
    }

    public GeometryObject() {
        FDimensions = 1;
        FID = "";
    }

    public void setID(String aID) {
        FID = aID;
    }

    public String getID() {
        return FID;
    }

}
