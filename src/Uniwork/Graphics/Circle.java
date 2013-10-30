package Uniwork.Graphics;

public class Circle extends Ellipse {

    public Circle() {
        this(0.0);
    }

    public Circle(double aRadius) {
        super(aRadius, aRadius);
        InternalUpdate();
    }

    public void setRadius(double aValue) {
        setRadiusX(aValue);
        setRadiusY(aValue);
    }

    public double getRadius() {
        return getRadiusX();
    }

    public double getDiameter() {
        return getDiameterX();
    }

    public Integer getDiameterAsInt() {
        return (int)getDiameterXAsInt();
    }

}
