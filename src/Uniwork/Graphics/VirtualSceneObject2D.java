package Uniwork.Graphics;

public class VirtualSceneObject2D extends VirtualSceneObject{

    protected Point2D FPosition;

    public VirtualSceneObject2D(Point2D aPosition) {
        super();
        FPosition = aPosition;
    }

    public Point2D getPosition() {
        return FPosition;
    }

    public void setPosition(Point2D aPosition) {
        FPosition = aPosition;
    }

}
