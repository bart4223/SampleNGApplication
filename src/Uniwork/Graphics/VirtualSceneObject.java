package Uniwork.Graphics;

public class VirtualSceneObject {

    protected Integer FID;
    protected String FName;
    protected String FDescription;
    protected GeometryObject FHull;

    public VirtualSceneObject() {
        this(0, "", "");
    }

    public VirtualSceneObject(Integer aID, String aName, String aDescription) {
        FID = aID;
        FName = aName;
        FDescription = aDescription;
        FHull = null;
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

    public GeometryObject getHull() {
        return FHull;
    }

}
