package Uniwork.Graphics;

import Uniwork.Base.NGObject;

public class NGRegion2D extends NGObject {

    protected NGPoint2D FTopLeft;
    protected NGPoint2D FBottomRight;

    public NGRegion2D(double aTLX, double aTLY, double aBRX, double aBRY) {
        super();
        FTopLeft = new NGPoint2D(aTLX, aTLY);
        FBottomRight = new NGPoint2D(aBRX, aBRY);
    }

    public Boolean IsPointInRegion(NGPoint2D aPoint) {
        return  FTopLeft.getX() <= aPoint.getX() && aPoint.getX() <= FBottomRight.getX()
                    &&  FTopLeft.getY() <= aPoint.getY() && aPoint.getY() <= FBottomRight.getY();
    }

}
