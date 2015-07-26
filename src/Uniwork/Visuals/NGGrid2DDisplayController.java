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
        super.DoRender();
        if (!DrawGrid) return;
        double x = 0 - getViewPositionX();
        double y = 0 - getViewPositionY();
        Integer index = 0;
        Color color;
        for(double i = x; i <= x + GridWidth; i = i + GridDistance) {
            FGC.beginPath();
            FGC.moveTo(i, y);
            FGC.lineTo(i, GridHeight);
            color = GridColor;
            if (AlternateGridColor && index % 2 == 0)
                color = GridColor.darker();
            FGC.setStroke(color);
            FGC.setLineWidth(GridLineWidth);
            FGC.stroke();
            FGC.closePath();
            index = index + 1;
        }
        index = 0;
        for(double i = y; i < y + GridHeight; i = i + GridDistance) {
            FGC.beginPath();
            FGC.moveTo(x, i);
            FGC.lineTo(GridWidth, i);
            color = GridColor;
            if (AlternateGridColor && index % 2 == 0)
                color = GridColor.darker();
            FGC.setStroke(color);
            FGC.setLineWidth(GridLineWidth);
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
        GridHeight = FHeight;
        GridWidth = FWidth;
        GridLineWidth = 1;
        AlternateGridColor = true;
    }

    public int GridDistance;
    public Color GridColor;
    public boolean DrawGrid;
    public double GridWidth;
    public double GridHeight;
    public int GridLineWidth;
    public boolean AlternateGridColor;

}
