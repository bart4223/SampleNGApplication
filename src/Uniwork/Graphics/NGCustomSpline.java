package Uniwork.Graphics;

import Uniwork.Base.NGObject;

public abstract class NGCustomSpline extends NGObject {

    protected String FName;

    protected void DoCalculate() {

    }

    public NGCustomSpline(String aName) {
        FName = aName;
    }

    public String getName() {
        return FName;
    }

    public void Calculate() {
        DoCalculate();
    }

}
