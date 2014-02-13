package Uniwork.Graphics;

public class Circle extends Ellipse {

    public Circle() {
        this(0.0);
    }

    public Circle(double aRadius) {
        this(0.0, 0.0, aRadius);
        InternalUpdate();
    }

    public Circle(double aX, double aY, double aRadius) {
        super(aX, aY, aRadius, aRadius);
        InternalUpdate();
    }

    public void setRadius(double aValue) {
        setRadiusX(aValue);
        setRadiusY(aValue);
    }

    public double getRadius() {
        return getRadiusX();
    }

    public int getRadiusAsInt() {
        return getRadiusXAsInt();
    }

    public double getDiameter() {
        return getDiameterX();
    }

    public Integer getDiameterAsInt() {
        return (int)getDiameterXAsInt();
    }

}
