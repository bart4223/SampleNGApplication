package Uniwork.Graphics;

import Uniwork.Base.NGObject;

public class NGRegion2D extends NGObject {

    protected NGPoint2D FTopLeft;
    protected NGPoint2D FBottomRight;

    public NGRegion2D(Integer aTLX, Integer aTLY, Integer aBRX, Integer aBRY) {
        super();
        FTopLeft = new NGPoint2D(aTLX, aTLY);
        FBottomRight = new NGPoint2D(aBRX, aBRY);
    }

    public Boolean IsPointInRegion(NGPoint2D aPoint) {
        return aPoint.getX() >= FTopLeft.getX() && aPoint.getX() <= FBottomRight.getX()
                    &&  aPoint.getY() >= FTopLeft.getY() && aPoint.getX() <= FBottomRight.getY();
    }

}
