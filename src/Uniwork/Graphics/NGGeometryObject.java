package Uniwork.Graphics;

public class NGGeometryObject {

    protected Integer FDimensions;
    protected String FID;

    protected void InternalUpdate() {
        // NOP
    }

    public NGGeometryObject() {
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
