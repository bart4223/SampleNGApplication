package Uniwork.Graphics;

public class NGRectangle extends NGGeometryObject2D {

    protected double FA;
    protected double FB;
    protected NGPoint2D FMiddlePoint;

    public NGRectangle() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public NGRectangle(double aA, double aB, double aX, double aY) {
        super();
        FMiddlePoint = new NGPoint2D(aX, aY);
        FA = aA;
        FB = aB;
    }

    public void setA(double aA) {
        FA = aA;
    }

    public double getA( ) {
        return FA;
    }

    public int getAAsInt( ) { return (int)FA; }

    public void setB(double aB) {
        FB = aB;
    }

    public double getB( ) {
        return FB;
    }

    public int getBAsInt( ) { return (int)FB; }

    public void setMiddlePoint(double aX, double aY) {
        FMiddlePoint.setX(aX);
        FMiddlePoint.setY(aY);
    }

    public NGPoint2D getMiddlePoint() {
        return FMiddlePoint;
    }

    public void setMiddlePointX(double value) {
        FMiddlePoint.setX(value);
    }

    public double getMiddlePointX() {
        return FMiddlePoint.getX();
    }

    public void setMiddlePointY(double value) {
        FMiddlePoint.setY(value);
    }

    public double getMiddlePointY() {
        return FMiddlePoint.getY();
    }

}
