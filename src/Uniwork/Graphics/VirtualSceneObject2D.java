package Uniwork.Graphics;

public class VirtualSceneObject2D extends VirtualSceneObject{

    protected NGPoint2D FPosition;

    public VirtualSceneObject2D() {
        this(new NGPoint2D(0, 0));
    }

    public VirtualSceneObject2D(NGPoint2D aPosition) {
        super();
        FPosition = aPosition;
    }

    public NGPoint2D getPosition() {
        return FPosition;
    }

    public void setPosition(NGPoint2D aPosition) {
        FPosition = aPosition;
    }

}
