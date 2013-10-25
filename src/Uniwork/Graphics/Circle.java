package Uniwork.Graphics;


public class Circle extends GeometryObject {

    protected double FRadius;
    protected double FDiameter;

    @Override
    protected void InternalUpdate() {
        super.InternalUpdate();
        FDiameter = FRadius * 2;
    }

    public Circle() {
        this(0.0);
    }

    public Circle(double aRadius) {
        FRadius = aRadius;
        InternalUpdate();
    }

    public void setRadius(double aValue) {
        FRadius = aValue;
        InternalUpdate();
    }

    public double getRadius() {
        return FRadius;
    }

    public double getDiameter() {
        return FDiameter;
    }

    public Integer getDiameterAsInt() {
        return (int)getDiameter();
    }

}
