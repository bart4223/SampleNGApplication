package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGCircleDisplayController extends NGEllipseDisplayController {

    @Override
    protected void drawCircle(int aX, int aY, int aRadius, Color aColor) {
        super.drawCircle(aX, aY, aRadius, aColor);
        if (Selected)
            drawPixel(aX, aY, aColor);
    }

    public NGCircleDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

}
