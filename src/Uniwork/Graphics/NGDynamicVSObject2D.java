package Uniwork.Graphics;

public class NGDynamicVSObject2D extends NGVirtualSceneObject2D {

    protected Integer FMoveDelaying;
    protected NGVector2D FDirection;
    protected double FVelocity;
    protected NGCollisionDetectionCallback FCollisionDetectionCallback;

    protected Integer getMoveDelaying() {
        return (int)(1 / FVelocity*FDirection.getAmount() * 10);
    }

    @Override
    protected void DoCollisionDetected(NGVirtualSceneObject aVirtualSceneObject) {
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

    protected Boolean DoCalcNewDirection(NGVirtualSceneObject aVirtualSceneObject) {
        return false;
    }

    protected NGCollisionDetectionCallback getCollisionDetectionCallback() {
        if (FCollisionDetectionCallback == null && FCallback != null) {
            if (FCallback instanceof NGCollisionDetectionCallback) {
                FCollisionDetectionCallback = (NGCollisionDetectionCallback)FCallback;
            }
        }
        return FCollisionDetectionCallback;
    }

    protected void CallCollisionDetectionCallback() {
        NGCollisionDetectionCallback lCollisionDetectionCallback = getCollisionDetectionCallback();
        if (lCollisionDetectionCallback != null) {
            lCollisionDetectionCallback.DetectCollision(this);
        }
    }

    public NGDynamicVSObject2D() {
        this(new NGPoint2D(0, 0), new NGVector2D(0, 0), 0);
    }

    public NGDynamicVSObject2D(NGPoint2D aPoint, NGVector2D aDirection, double aVelocity) {
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

    public NGVector2D getDirection() {
        return FDirection;
    }

    public void setDirection(NGVector2D aValue) {
        FDirection = aValue;
    }

    public void setVelocity(double aValue) {
        FVelocity = aValue;
    }

    public double getVelocity() {
        return FVelocity;
    }

}
