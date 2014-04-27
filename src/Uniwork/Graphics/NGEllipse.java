package Uniwork.Graphics;

public class NGEllipse extends NGGeometryObject2D {

    protected double FRadiusX;
    protected double FDiameterX;
    protected double FRadiusY;
    protected double FDiameterY;
    protected NGPoint2D FMiddlePoint;

    @Override
    protected void InternalUpdate() {
        super.InternalUpdate();
        FDiameterX = FRadiusX * 2;
        FDiameterY = FRadiusY * 2;
    }

    public NGEllipse() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public NGEllipse(double aX, double aY, double aRadiusX, double aRadiusY) {
        FRadiusX = aRadiusX;
        FRadiusY = aRadiusY;
        FMiddlePoint = new NGPoint2D(aX, aY);
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

    public NGPoint2D getMiddlePoint() {
        return FMiddlePoint;
    }

    public void setMiddlePoint(double aX, double aY) {
        FMiddlePoint.setX(aX);
        FMiddlePoint.setY(aY);
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
