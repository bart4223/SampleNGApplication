package Uniwork.Graphics;

public class DynamicVSObject2D extends VirtualSceneObject2D {

    protected Integer FMoveDelaying;
    protected Vector2D FDirection;
    protected double FVelocity;
    protected CollisionDetectionCallback FCollisionDetectionCallback;

    protected Integer getMoveDelaying() {
        return (int)(1 / FVelocity*FDirection.getAmount() * 10);
    }

    @Override
    protected void DoCollisionDetected(VirtualSceneObject aVirtualSceneObject) {
        super.DoCollisionDetected(aVirtualSceneObject);
        if (DoCalcNewDirection(aVirtualSceneObject)) {
            DoMoving();
        }
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

    protected Boolean DoCalcNewDirection(VirtualSceneObject aVirtualSceneObject) {
        return false;
    }

    protected CollisionDetectionCallback getCollisionDetectionCallback() {
        if (FCollisionDetectionCallback == null && FCallback != null) {
            if (FCallback instanceof CollisionDetectionCallback) {
                FCollisionDetectionCallback = (CollisionDetectionCallback)FCallback;
            }
        }
        return FCollisionDetectionCallback;
    }

    protected void CallCollisionDetectionCallback() {
        CollisionDetectionCallback lCollisionDetectionCallback = getCollisionDetectionCallback();
        if (lCollisionDetectionCallback != null) {
            lCollisionDetectionCallback.DetectCollision(this);
        }
    }

    public DynamicVSObject2D() {
        this(new NGPoint2D(0, 0), new Vector2D(0, 0), 0);
    }

    public DynamicVSObject2D(NGPoint2D aPoint, Vector2D aDirection, double aVelocity) {
        super(aPoint);
        FDirection = aDirection;
        FVelocity = aVelocity;
        FMoveDelaying = 0;
        FCollisionDetectionCallback = null;
    }

    public void Moving() {
        if (FMoveDelaying <= 0) {
            FMoveDelaying = getMoveDelaying();
            DoMoving();
            CallCollisionDetectionCallback();
        }
        else {
            FMoveDelaying = FMoveDelaying - 1;
        }
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
