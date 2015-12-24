package Uniwork.Graphics;

import Uniwork.Base.NGComponent;

import java.util.ArrayList;
import java.util.Iterator;

public class NGSplineManager extends NGComponent {

    protected ArrayList<NGCustomSpline> FSplines;

    public NGSplineManager() {
        this(null);
    }

    public NGSplineManager(NGComponent aOwner) {
        this(aOwner, "");
    }

    public NGSplineManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FSplines = new ArrayList<NGCustomSpline>();
    }

    public Iterator<NGCustomSpline> getSplines() {
        return FSplines.iterator();
    }

    public void addSpline(NGCustomSpline aSpline) {
        FSplines.add(aSpline);
    }

}
