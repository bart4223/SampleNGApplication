package Uniwork.Graphics;

public class NGSquare extends NGStaticVSObject2D {

    public NGSquare() {
        this(new NGPoint2D(0.0, 0.0), 0.0, 0.0);
    }

    public NGSquare(NGPoint2D aPosition, double aSideA, double aSideB) {
        super(aPosition);
        FHull = new NGRectangle(aSideA, aSideB, aPosition.getX(), aPosition.getY());
    }

    @Override
    public NGRectangle getHull() {
        return (NGRectangle)FHull;
    }

    public double getSideA() {
        return getHull().getA();
    }

    public double getSideB() {
        return getHull().getB();
    }

    public NGPoint2D getTopLeft() {
        return new NGPoint2D(getPosition().getX() - getSideA() / 2, getPosition().getY() + getSideB() / 2);
    }

    public NGPoint2D getBottomRight() {
        return new NGPoint2D(getPosition().getX() + getSideA() / 2, getPosition().getY() - getSideB() / 2);
    }

    public Boolean IsPointInside(NGPoint2D aPoint, double aOffset) {
        NGPoint2D lTL = getTopLeft();
        lTL.setX(lTL.getX()-aOffset);
        lTL.setY(lTL.getY()+aOffset);
        NGPoint2D lBR = getBottomRight();
        lBR.setX(lBR.getX()+aOffset);
        lBR.setY(lBR.getY()-aOffset);
        return (aPoint.getX() >= lTL.getX() && aPoint.getX() <= lBR.getX() && aPoint.getY() <= lTL.getY() && aPoint.getY() >= lBR.getY());
    }

}
