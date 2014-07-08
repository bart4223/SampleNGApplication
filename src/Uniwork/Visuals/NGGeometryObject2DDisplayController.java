package Uniwork.Visuals;

import Uniwork.Graphics.NGGeometryObject2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public abstract class NGGeometryObject2DDisplayController extends NGDisplayController {

    protected void drawPixelFrame(int aX, int aY) {
        double x = aX * FPixelSize - getViewPositionX();
        double y = aY * FPixelSize - getViewPositionY();
        FGC.setLineWidth(2);
        FGC.setStroke(Color.MAGENTA);
        FGC.strokeRect(x, y, getPixelSize(), getPixelSize());
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
