package Uniwork.Visuals;

import Uniwork.Graphics.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SevenSegmentDisplayController {

    protected GraphicsContext FGC;
    protected Canvas FCanvas;
    protected Point2D FPosition;
    protected int FWidth;
    protected int FHeight;

    public SevenSegmentDisplayController(Canvas aCanvas) {
        this(aCanvas,new Point2D(0,0),10,14);
    }

    public SevenSegmentDisplayController(Canvas aCanvas, Point2D aPosition, int aWidth, int aHeight) {
        FCanvas = aCanvas;
        FWidth = aWidth;
        FHeight = aHeight;
    }

    public void Initialize() {
        FGC = FCanvas.getGraphicsContext2D();
    }

    public void Display() {
        FGC.setFill(Color.BLUE);
        FGC.fillRect(FPosition.getXAsInt(), FPosition.getYAsInt(), FWidth, FHeight);
    }

}