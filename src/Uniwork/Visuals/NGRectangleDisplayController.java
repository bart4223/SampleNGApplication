package Uniwork.Visuals;

import Uniwork.Graphics.NGRectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGRectangleDisplayController extends NGGeometryObject2DDisplayController {

    @Override
    protected void drawRectangle(int aX, int aY, int aA, int aB, Color aColor) {
        super.drawRectangle(aX, aY, aA, aB, aColor);
        if (Selected)
            drawPixel(aX, aY, aColor);
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        NGRectangle Rectangle = (NGRectangle)GeometryObject;
        drawRectangle(Rectangle.getMiddlePoint().getXAsInt(), Rectangle.getMiddlePoint().getYAsInt(), Rectangle.getAAsInt(), Rectangle.getBAsInt(), GeometryObjectColor);
    }

    public NGRectangleDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

}
