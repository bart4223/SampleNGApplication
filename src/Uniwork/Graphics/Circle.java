package Uniwork.Graphics;


public class Circle extends GeometryObject {

    protected Double FRadius;

    public Circle() {
        FRadius = 0.0;
    }

    public Circle(double aRadius) {
        FRadius = aRadius;
    }

    public void setRadius(double aValue) {
        FRadius = aValue;
    }

    public double getRadius() {
        return FRadius;
    }

}
