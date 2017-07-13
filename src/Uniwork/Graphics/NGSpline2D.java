package Uniwork.Graphics;

import com.sun.scenario.animation.SplineInterpolator;
import javafx.animation.Interpolator;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGSpline2D extends NGCustomSpline {

    protected CopyOnWriteArrayList<NGPoint2D> FControlPoints;
    protected CopyOnWriteArrayList<SplineInterpolator> FSegments;

    @Override
    protected void DoCalculate() {
        super.DoCalculate();
        NGPoint2D cp0 = null;
        for (NGPoint2D cp: FControlPoints) {
            if (cp0 != null) {
                SplineInterpolator segment = (SplineInterpolator)Interpolator.SPLINE(cp0.getX(), cp0.getY(), cp.getX(), cp.getY());
                FSegments.add(segment);
                cp0 = null;
            }
            else {
                cp0 = cp;
            }
        }
        dumpSegments();
    }

    protected void dumpSegments() {
        for (SplineInterpolator segment : FSegments) {
            for (double x = 0.0; x <= 1.0; x = x + 0.1) {
                double y = segment.curve(x);
                System.out.println(String.format("x:%.2f,y:%.2f",x, y));
            }
        }
    }

    public NGSpline2D(String aName) {
        super(aName);
        FControlPoints = new CopyOnWriteArrayList<NGPoint2D>();
        FSegments = new CopyOnWriteArrayList<SplineInterpolator>();
    }

    public Iterator<NGPoint2D> getControlPoints() {
        return FControlPoints.iterator();
    }

    public void addControlPoint(Double aX, Double aY) {
        NGPoint2D cp = new NGPoint2D(aX, aY);
        FControlPoints.add(cp);
    }

}
