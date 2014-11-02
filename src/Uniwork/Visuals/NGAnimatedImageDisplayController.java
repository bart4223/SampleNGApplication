package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGAnimatedImageDisplayController extends NGImageDisplayController {

    @Override
    protected String resolveImageName(NGDisplayControllerLayerItem aItem, String aImageName) {
        return String.format(aImageName, aItem.ImageNumber, AnimationIndex);
    }

    public NGAnimatedImageDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName, aImagename);
        AnimationIndex = 0;
    }

    public Integer AnimationIndex;

}
