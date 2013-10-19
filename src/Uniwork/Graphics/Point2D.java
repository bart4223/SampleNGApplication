package Uniwork.Graphics;

public class Point2D {

    protected Integer FX;
    protected Integer FY;

    public Point2D() {
        FX = 0;
        FY = 0;
    }

    public Point2D(Integer aX, Integer aY) {
        FX = aX;
        FY = aY;
    }

    public void setX(Integer aValue) {
        FX = aValue;
    }

    public Integer getX() {
        return FX;
    }

    public void setY(Integer aValue) {
        FY = aValue;
    }

    public Integer getY() {
        return FY;
    }

}
