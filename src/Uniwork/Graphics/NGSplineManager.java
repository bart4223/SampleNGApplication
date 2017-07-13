package Uniwork.Graphics;

import Uniwork.Base.NGComponent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGSplineManager extends NGComponent {

    protected CopyOnWriteArrayList<NGCustomSpline> FSplines;

    protected void DoCalculate() {
        for (NGCustomSpline spline: FSplines ) {
            spline.Calculate();
        }
    }

    public NGSplineManager() {
        this(null);
    }

    public NGSplineManager(NGComponent aOwner) {
        this(aOwner, "");
    }

    public NGSplineManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FSplines = new CopyOnWriteArrayList<NGCustomSpline>();
    }

    public Iterator<NGCustomSpline> getSplines() {
        return FSplines.iterator();
    }

    public void addSpline(NGCustomSpline aSpline) {
        FSplines.add(aSpline);
    }

    public void Calculate() {
        DoCalculate();
    }

}
