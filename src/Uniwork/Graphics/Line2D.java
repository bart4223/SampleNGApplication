package Uniwork.Graphics;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Line2D extends GeometryObject2D {

    protected Point2D FA;
    protected Point2D FB;

    public Line2D() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public Line2D(double aAX, double aAY, double aBX, double aBY) {
        FA = new Point2D(aAX, aAY);
        FB = new Point2D(aBX, aBY);
    }

    public Point2D getA() {
        return FA;
    }

    public Point2D getB() {
        return FB;
    }

    public double getAmount() {
        double dx = FA.getX() - FB.getX();
        double dy = FA.getY() - FB.getY();
        return sqrt(pow(dx, 2.0)+pow(dy, 2.0));
    }

    public double getGradient() {
        double dx = FA.getX() - FB.getX();
        double dy = FA.getY() - FB.getY();
        if (dx!=0) {
            return dy/dx;
        }
        return 0;
    }
}
