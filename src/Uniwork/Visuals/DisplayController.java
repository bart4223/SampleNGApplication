package Uniwork.Visuals;

import Uniwork.Graphics.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;

public class DisplayController extends Uniwork.Base.Object {

    protected Boolean FInitialized;
    protected GraphicsContext FGC;
    protected Canvas FCanvas;
    protected String FName;
    protected Point2D FPosition;
    protected int FPixelSize;
    protected Color FBackgroundColor;
    protected double FWidth;
    protected double FHeight;

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

    protected void RecalculateDimensions() {
    }

    protected void InternalUpdate() {
        RecalculateDimensions();
    }

    protected void DoBeforeRender() {

    }

    protected void DoRender() {

    }

    protected void DoAfterRender() {

    }

    protected void DoInitialize() {

    }

    public DisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }


    public DisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, new Point2D(0, 0));
    }

    public DisplayController(Canvas aCanvas, String aName, Point2D aPosition) {
        super();
        FInitialized = false;
        FCanvas = aCanvas;
        FName = aName;
        FWidth = 0;
        FHeight = 0;
        if (aCanvas != null) {
            FWidth = aCanvas.getWidth();
            FHeight = aCanvas.getHeight();
        }
        FPosition = aPosition;
        FBackgroundColor = Color.WHITE;
        FPixelSize = 1;
        BaseWidth = 0;
        BaseHeight = 0;
        FGC = null;
    }

    public int BaseWidth;
    public int BaseHeight;

    public void Initialize() {
        if (!FInitialized) {
            if (FCanvas != null)
                FGC = FCanvas.getGraphicsContext2D();
            DoInitialize();
            InternalUpdate();
            FInitialized = true;
        }
    }

    public void Render() {
        if (FInitialized) {
            DoBeforeRender();
            DoRender();
            DoAfterRender();
        }
    }

    public void setName(String aValue) {
        FName = aValue;
    }

    public String getName() {
        return FName;
    }

    public void setPixelSize(int aValue) {
        FPixelSize = aValue;
        InternalUpdate();
    }

    public int getPixelSize() {
        return FPixelSize;
    }

    public Point2D getPosition() {
        return FPosition;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
        InternalUpdate();
    }

    public void setBackgroundColor(Color aValue) {
        FBackgroundColor = aValue;
        InternalUpdate();
    }

    public Color getBackgroundColor() {
        return FBackgroundColor;
    }

    public double getWidth() {
        return FWidth;
    }

    public double getHeight() {
        return FHeight;
    }

}
