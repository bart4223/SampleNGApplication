package Uniwork.Graphics;


public class Ellipse extends GeometryObject{

    protected double FRadiusX;
    protected double FDiameterX;
    protected double FRadiusY;
    protected double FDiameterY;

    @Override
    protected void InternalUpdate() {
        super.InternalUpdate();
        FDiameterX = FRadiusX * 2;
        FDiameterY = FRadiusY * 2;
    }

    public Ellipse() {
        this(0.0, 0.0);
    }

    public Ellipse(double aRadiusX, double aRadiusY) {
        FRadiusX = aRadiusX;
        FRadiusY = aRadiusY;
        InternalUpdate();
    }

    public void setRadiusX(double aValue) {
        FRadiusX = aValue;
        InternalUpdate();
    }

    public double getRadiusX() {
        return FRadiusX;
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

    public double getDiameterY() {
        return FDiameterY;
    }

    public Integer getDiameterYAsInt() {
        return (int)getDiameterY();
    }

}
