package Uniwork.Visuals;

import Uniwork.Graphics.NGGeometryObject2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGGeometryObject2DDisplayController extends NGDisplayController {

    protected void drawPixelFrame(int aX, int aY) {
        int PX = aX * getPixelSize();
        int PY = aY * getPixelSize();
        FGC.setLineWidth(2);
        FGC.setStroke(Color.MAGENTA);
        FGC.strokeRect(PX, PY, getPixelSize(), getPixelSize());
    }

    @Override
    protected void drawPixel(int aX, int aY, Color aColor) {
        super.drawPixel(aX, aY, aColor);
        if (Selected) {
            drawPixelFrame(aX, aY);
        }
    }

    public NGGeometryObject2DDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        GeometryObject = null;
        GeometryObjectColor = Color.BLACK;
    }

    public NGGeometryObject2D GeometryObject;
    public Color GeometryObjectColor;
    public boolean Selected;

}
