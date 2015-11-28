package Uniwork.Visuals;

import Uniwork.Graphics.NGPoint2D;
import javafx.scene.Node;

public abstract class NGCustomAnimation2DItem extends NGCustomAnimationItem {

    protected NGPoint2D FPositionFrom;
    protected NGPoint2D FPositionTo;

    public NGCustomAnimation2DItem(String aName, Node aNode, Double aDuration) {
        super(aName, aNode, aDuration);
        FPositionFrom = new NGPoint2D(0, 0);
        FPositionTo = new NGPoint2D(0, 0);
    }

}
