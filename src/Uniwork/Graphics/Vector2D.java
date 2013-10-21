package Uniwork.Graphics;

import static java.lang.Math.sqrt;

public class Vector2D {

    protected Integer FX;
    protected Integer FY;

    public Vector2D() {
        FX = 0;
        FY = 0;
    }

    public Vector2D(Integer aX, Integer aY) {
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

    public double getAmount() {
        return sqrt(FX*FX+FY*FY);
    }

}
