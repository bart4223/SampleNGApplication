package Uniwork.Graphics;

public class Rectangle extends GeometryObject2D{

    protected double FA;
    protected double FB;
    protected Point2D FMiddlePoint;

    public Rectangle() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public Rectangle(double aA, double aB, double aX, double aY) {
        super();
        FMiddlePoint = new Point2D(aX, aY);
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

    public Point2D getMiddlePoint() {
        return FMiddlePoint;
    }

}
