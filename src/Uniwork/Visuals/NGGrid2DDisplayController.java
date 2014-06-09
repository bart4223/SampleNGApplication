package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGGrid2DDisplayController extends NGDisplayController {

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.clearRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoRender() {
        if (!DrawGrid) return;
        double x = 0 - getViewPositionX();
        double y = 0 - getViewPositionY();
        Integer index = 0;
        Color color;
        for(double i = x; i <= x + FWidth; i = i + GridDistance) {
            FGC.beginPath();
            FGC.moveTo(i, y);
            FGC.lineTo(i, FWidth);
            if (index%2 == 0)
                color = GridColor.darker();
            else
                color = GridColor;
            FGC.setStroke(color);
            FGC.setLineWidth(1);
            FGC.stroke();
            FGC.closePath();
            index = index + 1;
        }
        index = 0;
        for(double i = y; i < y + FHeight; i = i + GridDistance) {
            FGC.beginPath();
            FGC.moveTo(x, i);
            FGC.lineTo(FWidth, i);
            if (index%2 == 0)
                color = GridColor.darker();
            else
                color = GridColor;
            FGC.setStroke(color);
            FGC.setLineWidth(1);
            FGC.stroke();
            FGC.closePath();
            index = index + 1;
        }
    }

    public NGGrid2DDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public NGGrid2DDisplayController(Canvas aCanvas, String aName)  {
        super(aCanvas, aName);
        GridDistance = 1;
        GridColor = Color.BLACK;
        DrawGrid = true;
    }

    public int GridDistance;
    public Color GridColor;
    public boolean DrawGrid;

}
