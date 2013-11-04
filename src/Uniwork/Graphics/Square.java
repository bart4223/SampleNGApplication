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

}
