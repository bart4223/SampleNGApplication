package Uniwork.Graphics;

public class Rectangle extends GeometryObject2D{

    protected double FA;
    protected double FB;

    public Rectangle() {
        this(0.0, 0.0);
    }

    public Rectangle(double aA, double aB) {
        super();
        FA = aA;
        FB = aB;
    }

    public void setA(double aA) {
        FA = aA;
    }

    public double getA( ) {
        return FA;
    }

    public void setB(double aB) {
        FB = aB;
    }

    public double getB( ) {
        return FB;
    }

}
