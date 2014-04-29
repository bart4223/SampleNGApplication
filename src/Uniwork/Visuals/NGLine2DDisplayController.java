package Uniwork.Visuals;

import Uniwork.Graphics.NGLine2D;
import javafx.scene.canvas.Canvas;

public class NGLine2DDisplayController extends NGGeometryObject2DDisplayController {

    @Override
    protected void DoRender() {
        super.DoRender();
        NGLine2D Line = (NGLine2D)GeometryObject;
        drawLine(Line.getA().getXAsInt(), Line.getA().getYAsInt(), Line.getB().getXAsInt(), Line.getB().getYAsInt(), GeometryObjectColor);
    }

    public NGLine2DDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

}
