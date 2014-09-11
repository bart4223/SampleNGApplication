package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGImageDisplayController extends NGDisplayController {

    @Override
    protected void DoRender() {
        super.DoRender();
        if (!IsClipRect()) {
            double x = getPositionX() - getViewPositionX();
            double y = getPositionY() - getViewPositionY();
            drawImage(x, y, FWidth, FHeight);
        }
    }

    public NGImageDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, "");
    }

    public NGImageDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName);
        NGDisplayControllerLayerItem layer = getLayer(CLAYERBACKGROUND);
        layer.setImageName(aImagename);
    }

}
