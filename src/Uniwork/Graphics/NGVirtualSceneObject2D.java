package Uniwork.Graphics;

public class NGVirtualSceneObject2D extends NGVirtualSceneObject {

    protected NGPoint2D FPosition;

    public NGVirtualSceneObject2D() {
        this(new NGPoint2D(0, 0));
    }

    public NGVirtualSceneObject2D(NGPoint2D aPosition) {
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
