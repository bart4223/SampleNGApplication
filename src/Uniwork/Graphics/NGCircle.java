package Uniwork.Graphics;

public class NGCircle extends NGEllipse {

    public NGCircle() {
        this(0.0);
    }

    public NGCircle(double aRadius) {
        this(0.0, 0.0, aRadius);
        InternalUpdate();
    }

    public NGCircle(double aX, double aY, double aRadius) {
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
