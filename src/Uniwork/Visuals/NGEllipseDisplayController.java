package Uniwork.Visuals;

import Uniwork.Graphics.NGEllipse;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGEllipseDisplayController extends NGGeometryObject2DDisplayController {

    @Override
    protected void drawEllipse(int aX, int aY, int aRadiusX, int aRadiusY, Color aColor) {
        super.drawEllipse(aX, aY, aRadiusX, aRadiusY, aColor);
        if (Selected)
            drawPixel(aX, aY, aColor);
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        NGEllipse Ellipse = (NGEllipse)GeometryObject;
        drawEllipse(Ellipse.getMiddlePoint().getXAsInt(), Ellipse.getMiddlePoint().getYAsInt(), Ellipse.getRadiusXAsInt(), Ellipse.getRadiusYAsInt(), GeometryObjectColor);
    }

    public NGEllipseDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

}
