package Uniwork.Graphics;

public class VirtualSceneObject {

    protected Integer FID;
    protected String FName;
    protected String FDescription;
    protected NGGeometryObject FHull;
    protected Object FCallback;
    protected Boolean FSelected;

    protected Boolean DoDetectCollision(VirtualSceneObject aVirtualSceneObject) {
        return false;
    }

    protected void DoCollisionDetected(VirtualSceneObject aVirtualSceneObject) {

    }

    public VirtualSceneObject() {
        this(0, "", "");
    }

    public VirtualSceneObject(Integer aID, String aName, String aDescription) {
        FID = aID;
        FName = aName;
        FDescription = aDescription;
        FHull = null;
        FCallback = null;
        FSelected = false;
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

    public void setSelected(Boolean aValue) {
        FSelected = aValue;
    }

    public Boolean getSelected( ) {
        return FSelected;
    }

    public void setDescription(String aDescription) {
        FDescription = aDescription;
    }

    public String getDescription( ) {
        return FDescription;
    }

    public NGGeometryObject getHull() {
        return FHull;
    }

    public void setCallback(Object aCallback) {
        FCallback = aCallback;
    }

    public Object getCallback() {
        return FCallback;
    }

    public Boolean DetectCollision(VirtualSceneObject aVirtualSceneObject) {
        Boolean lResult = false;
        if (!aVirtualSceneObject.equals(this)) {
            lResult = DoDetectCollision(aVirtualSceneObject);
            if (lResult) {
                CollisionDetected(aVirtualSceneObject);
            }
        }
        return lResult;
    }

    public void CollisionDetected(VirtualSceneObject aVirtualSceneObject) {
        DoCollisionDetected(aVirtualSceneObject);
    }

    public void ToggleSelected() {
        setSelected(!getSelected());
    }

}
