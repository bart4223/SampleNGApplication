package Uniwork.Graphics;

public class DynamicVSObject2D extends VirtualSceneObject2D {

    protected Integer FMoveDelaying;
    protected Vector2D FDirection;
    protected double FVelocity;

    protected Integer getMoveDelaying() {
        return (int)(1 / FVelocity*FDirection.getAmount() * 10);
    }

    protected Boolean DoDetectCollision(VirtualSceneObject aVirtualSceneObject) {
        return false;
    }

    protected void DoMoving() {
        if (getDirection().getX() < 0)
            getPosition().setX(getPosition().getX() - 1);
        else
            getPosition().setX(getPosition().getX() + 1);
        if (getDirection().getY() < 0)
            getPosition().setY(getPosition().getY() - getDirection().getGradient());
        else
            getPosition().setY(getPosition().getY() + getDirection().getGradient());
    }

    public DynamicVSObject2D(Point2D aPoint, Vector2D aDirection, double aVelocity) {
        super(aPoint);
        FDirection = aDirection;
        FVelocity = aVelocity;
        FMoveDelaying = 0;
    }

    public void Moving() {
        if (FMoveDelaying <= 0) {
            FMoveDelaying = getMoveDelaying();
            DoMoving();
        }
        else {
            FMoveDelaying = FMoveDelaying - 1;
        }
    }

    public Boolean DetectCollision(VirtualSceneObject aVirtualSceneObject) {
        if (!aVirtualSceneObject.equals(this))
            return DoDetectCollision(aVirtualSceneObject);
        else
            return false;
    }

    public Vector2D getDirection() {
        return FDirection;
    }

    public void setDirection(Vector2D aValue) {
        FDirection = aValue;
    }

    public void setVelocity(double aValue) {
        FVelocity = aValue;
    }

    public double getVelocity() {
        return FVelocity;
    }

}
