package Uniwork.Graphics;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point2D extends GeometryObject{

    protected double FX;
    protected double FY;

    public Point2D() {
        this(0.0, 0.0);
    }

    public Point2D(double aX, double aY) {
        FX = aX;
        FY = aY;
        FDimensions = 2;
    }

    public void setX(double aValue) {
        FX = aValue;
    }

    public double getX() {
        return FX;
    }

    public Integer getXAsInt() {
        return (int)getX();
    }

    public void setY(double aValue) {
        FY = aValue;
    }

    public double getY() {
        return FY;
    }

    public Integer getYAsInt() {
        return (int)getY();
    }

    public double getEuclidDistance(Point2D aPoint) {
        return sqrt(pow(getX() - aPoint.getX(), 2.0) + pow(getY() - aPoint.getY(), 2.0));
    }

}