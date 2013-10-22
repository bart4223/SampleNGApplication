package Uniwork.Graphics;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Vector2D {

    protected double FX;
    protected double FY;

    public Vector2D() {
        FX = 0.0;
        FY = 0.0;
    }

    public Vector2D(double aX, double aY) {
        FX = aX;
        FY = aY;
    }

    public void setX(double aValue) {
        FX = aValue;
    }

    public double getX() {
        return FX;
    }

    public void setY(double aValue) {
        FY = aValue;
    }

    public double getY() {
        return FY;
    }

    public double getAmount() {
        return sqrt(FX*FX+FY*FY);
    }

    public double getGradient( ) {
        return abs(FY/FX);
    }

}
