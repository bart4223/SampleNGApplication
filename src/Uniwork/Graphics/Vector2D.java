package Uniwork.Graphics;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector2D {

    protected double FX;
    protected double FY;
    protected double FAmount;
    protected double FGradient;

    protected void InternalUpdate() {
        FAmount = sqrt(pow(FX, 2.0)+pow(FY, 2.0));
        FGradient = 0;
        if (FX!=0)
            FGradient = abs(FY/FX);
    }

    public Vector2D() {
        this(0.0, 0.0);
    }

    public Vector2D(double aX, double aY) {
        FX = aX;
        FY = aY;
        InternalUpdate();
    }

    public void setX(double aValue) {
        FX = aValue;
        InternalUpdate();
    }

    public double getX() {
        return FX;
    }

    public void setY(double aValue) {
        FY = aValue;
        InternalUpdate();
    }

    public double getY() {
        return FY;
    }

    public double getAmount() {
        return FAmount;
    }

    public double getGradient( ) {
        return FGradient;
    }

    public Vector2D Add(Vector2D aVector) {
        return new Vector2D(getX()+aVector.getX(), getY()+aVector.getY());
    }

    public Vector2D Sub(Vector2D aVector) {
        return new Vector2D(getX()-aVector.getX(), getY()-aVector.getY());
    }

}
