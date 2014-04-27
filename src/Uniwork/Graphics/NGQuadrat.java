package Uniwork.Graphics;

public class NGQuadrat extends NGRectangle {

    public NGQuadrat() {
        this(0.0, 0.0, 0.0);
    }

    public NGQuadrat(double aA, double aX, double aY) {
        super(aA, aA, aX, aY);
    }

    public void setA(double aA) {
        FA = aA;
        FB = aA;
    }

    public void setB(double aB) {
        FA = aB;
        FB = aB;
    }

}
