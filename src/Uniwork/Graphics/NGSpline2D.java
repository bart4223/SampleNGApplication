package Uniwork.Graphics;

import java.util.ArrayList;
import java.util.Iterator;

public class NGSpline2D extends NGCustomSpline {

    protected ArrayList<NGPoint2D> FControlPoints;

    public NGSpline2D(String aName) {
        super(aName);
        FControlPoints = new ArrayList<NGPoint2D>();
    }

    public Iterator<NGPoint2D> getControlPoints() {
        return FControlPoints.iterator();
    }

    public void addControlPoint(Double aX, Double aY) {
        NGPoint2D controlpoint = new NGPoint2D(aX, aY);
        FControlPoints.add(controlpoint);
    }

}
