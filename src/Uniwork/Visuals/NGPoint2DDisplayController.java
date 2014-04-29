package Uniwork.Visuals;

import Uniwork.Graphics.NGPoint2D;
import javafx.scene.canvas.Canvas;

public class NGPoint2DDisplayController extends NGGeometryObject2DDisplayController {

    @Override
    protected void DoRender() {
        super.DoRender();
        NGPoint2D point = (NGPoint2D)GeometryObject;
        drawPixel(point.getXAsInt(), point.getYAsInt(), GeometryObjectColor);
    }

    public NGPoint2DDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

}
