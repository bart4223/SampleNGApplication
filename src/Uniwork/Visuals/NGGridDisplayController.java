package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGGridDisplayController extends NGDisplayController {

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.setFill(FBackgroundColor);
        FGC.fillRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoRender() {
        if (!DrawGrid) return;
        Integer index = 0;
        Color color;
        for(int i = 0; i <= FWidth; i = i + GridDistance) {
            FGC.beginPath();
            FGC.moveTo(i, 0);
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
        for(int i = 0; i < FHeight; i = i + GridDistance) {
            FGC.beginPath();
            FGC.moveTo(0, i);
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

    public NGGridDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public NGGridDisplayController(Canvas aCanvas, String aName)  {
        super(aCanvas, aName);
        GridDistance = 1;
        GridColor = Color.BLACK;
        DrawGrid = true;
    }

    public int GridDistance;
    public Color GridColor;
    public boolean DrawGrid;

}
