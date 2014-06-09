package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import Uniwork.Graphics.NGPoint2D;

public class NGDisplayView extends NGObject {

    protected NGPoint2D FTopLeft;
    protected NGPoint2D FBottomRight;

    public NGDisplayView(double aWidth, double aHeight) {
        super();
        FTopLeft = new NGPoint2D(0, 0);
        FBottomRight = new NGPoint2D(aWidth, aHeight);
    }

    public double getHeight() {
        return FBottomRight.getY() - FTopLeft.getY();
    }

    public double getWidth() {
        return FBottomRight.getX() - FTopLeft.getX();
    }

    public double getPositionX() {
        return FTopLeft.getX();
    }

    public double getPositionY() {
        return FTopLeft.getY();
    }

    public void setPosition(double aX, double aY) {
        double width = getWidth();
        double height = getHeight();
        FTopLeft.setX(aX);
        FTopLeft.setY(aY);
        FBottomRight.setX(aX + width);
        FBottomRight.setY(aY + height);
    }

}
