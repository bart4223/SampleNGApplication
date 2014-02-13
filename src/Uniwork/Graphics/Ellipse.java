package Uniwork.Graphics;

public class Ellipse extends GeometryObject2D{

    protected double FRadiusX;
    protected double FDiameterX;
    protected double FRadiusY;
    protected double FDiameterY;
    protected Point2D FMiddlePoint;

    @Override
    protected void InternalUpdate() {
        super.InternalUpdate();
        FDiameterX = FRadiusX * 2;
        FDiameterY = FRadiusY * 2;
    }

    public Ellipse() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public Ellipse(double aX, double aY, double aRadiusX, double aRadiusY) {
        FRadiusX = aRadiusX;
        FRadiusY = aRadiusY;
        FMiddlePoint = new Point2D(aX, aY);
        InternalUpdate();
    }

    public void setRadiusX(double aValue) {
        FRadiusX = aValue;
        InternalUpdate();
    }

    public double getRadiusX() {
        return FRadiusX;
    }

    public int getRadiusXAsInt() {
        return (int)FRadiusX;
    }

    public double getDiameterX() {
        return FDiameterX;
    }

    public Integer getDiameterXAsInt() {
        return (int)getDiameterX();
    }

    public void setRadiusY(double aValue) {
        FRadiusY = aValue;
        InternalUpdate();
    }

    public double getRadiusY() {
        return FRadiusY;
    }

    public int getRadiusYAsInt() {
        return (int)FRadiusY;
    }

    public double getDiameterY() {
        return FDiameterY;
    }

    public Integer getDiameterYAsInt() {
        return (int)getDiameterY();
    }

    public Point2D getMiddlePoint() {
        return FMiddlePoint;
    }

    public void setMiddlePoint(double aX, double aY) {
        FMiddlePoint.setX(aX);
        FMiddlePoint.setY(aY);
    }

}
