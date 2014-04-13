package Uniwork.Visuals;

import Uniwork.Graphics.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;

public class DisplayController {

    protected GraphicsContext FGC;
    protected Canvas FCanvas;
    protected Point2D FPosition;
    protected Color FBackgroundColor;
    protected int FPixelSize;
    protected String FName;

    protected void drawPixel(int aX, int aY, Color aColor) {
        int x = aX * FPixelSize;
        int y = aY * FPixelSize;
        FGC.setFill(aColor);
        FGC.fillRect(x, y, FPixelSize, FPixelSize);
    }

    protected void drawLine(int aX0, int aY0, int aX1, int aY1, Color aColor) {
        {
            int dx =  abs(aX1 - aX0), sx = aX0 < aX1 ? 1 : -1;
            int dy = -abs(aY1 - aY0), sy = aY0 < aY1 ? 1 : -1;
            int err = dx + dy, e2;
            for(;;){
                drawPixel(aX0, aY0, aColor);
                if (aX0 == aX1 && aY0 == aY1)
                    break;
                e2 = 2 * err;
                if (e2 > dy) { err += dy; aX0 += sx; }
                if (e2 < dx) { err += dx; aY0 += sy; }
            }
        }
    }

    protected void DoRender() {

    }

    public DisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, new Point2D(0, 0));
    }

    public DisplayController(Canvas aCanvas, String aName, Point2D aPosition) {
        FCanvas = aCanvas;
        FName = aName;
        FPosition = aPosition;
        FBackgroundColor = Color.WHITE;
        FPixelSize = 1;
    }

    public void Initialize() {
        FGC = FCanvas.getGraphicsContext2D();
    }

    public void Render() {
        Clear();
        DoRender();
    }

    public void Clear() {
        FGC.setFill(FBackgroundColor);
        FGC.fillRect(0, 0, FCanvas.getWidth(), FCanvas.getHeight());
    }

    public String getName() {
        return FName;
    }

    public void setBackgroundColor(Color aValue) {
        FBackgroundColor = aValue;
    }

    public Color getBackgroundColor() {
        return FBackgroundColor;
    }

    public void setPixelSize(int aValue) {
        FPixelSize = aValue;
    }

    public int getPixelSize() {
        return FPixelSize;
    }

}
