package Uniwork.Graphics;

public class Point2D {

    protected double FX;
    protected double FY;

    public Point2D() {
        FX = 0.0;
        FY = 0.0;
    }

    public Point2D(double aX, double aY) {
        FX = aX;
        FY = aY;
    }

    public void setX(double aValue) {
        FX = aValue;
    }

    public double getX() {
        return FX;
    }

    public Integer getXAsInt() {
        return (int)FX;
    }

    public void setY(double aValue) {
        FY = aValue;
    }

    public double getY() {
        return FY;
    }

    public Integer getYAsInt() {
        return (int)FY;
    }

}
