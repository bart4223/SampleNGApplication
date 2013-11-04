package Uniwork.Graphics;

public class Quadrat extends Rectangle {

    public Quadrat() {
        this(0.0);
    }

    public Quadrat(double aA) {
        super(aA, aA);
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
