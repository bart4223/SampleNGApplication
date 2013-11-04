package Uniwork.Graphics;

public class Square extends StaticVSObject2D {

    public Square() {
        this(new Point2D(0.0, 0.0), 0.0, 0.0);
    }

    public Square(Point2D aPosition, double aSideA, double aSideB) {
        super(aPosition);
        FHull = new Rectangle(aSideA, aSideB);
    }

    @Override
    public Rectangle getHull() {
        return (Rectangle)FHull;
    }

    public double getSideA() {
        return getHull().getA();
    }

    public double getSideB() {
        return getHull().getB();
    }

    public Point2D getTopLeft() {
        return new Point2D(getPosition().getX() - getSideA() / 2, getPosition().getY() + getSideB() / 2);
    }

    public Point2D getBottomRight() {
        return new Point2D(getPosition().getX() + getSideA() / 2, getPosition().getY() - getSideB() / 2);
    }

}
