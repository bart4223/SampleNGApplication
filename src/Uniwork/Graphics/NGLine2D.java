package Uniwork.Graphics;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class NGLine2D extends NGGeometryObject2D {

    protected NGPoint2D FA;
    protected NGPoint2D FB;

    public NGLine2D() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public NGLine2D(double aAX, double aAY, double aBX, double aBY) {
        FA = new NGPoint2D(aAX, aAY);
        FB = new NGPoint2D(aBX, aBY);
    }

    public NGPoint2D getA() {
        return FA;
    }

    public NGPoint2D getB() {
        return FB;
    }

    public void setA(double aX, double aY) {
        FA.setX(aX);
        FA.setY(aY);
    }

    public void setB(double aX, double aY) {
        FB.setX(aX);
        FB.setY(aY);
    }

    public double getAX() {
        return FA.getX();
    }

    public void setAX(double value) {
        FA.setX(value);
    }

    public double getAY() {
        return FA.getY();
    }

    public void setAY(double value) {
        FA.setY(value);
    }

    public double getBX() {
        return FB.getX();
    }

    public void setBX(double value) {
        FB.setX(value);
    }

    public double getBY() {
        return FB.getY();
    }

    public void setBY(double value) {
        FB.setY(value);
    }

    public double getAmount() {
        double dx = FA.getX() - FB.getX();
        double dy = FA.getY() - FB.getY();
        return sqrt(pow(dx, 2.0)+pow(dy, 2.0));
    }

    public double getGradient() {
        double dx = FA.getX() - FB.getX();
        double dy = FA.getY() - FB.getY();
        if (dx!=0) {
            return dy/dx;
        }
        return 0;
    }
}
