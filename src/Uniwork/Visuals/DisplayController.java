package Uniwork.Visuals;

import Uniwork.Graphics.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DisplayController extends Uniwork.Base.Object {

    protected GraphicsContext FGC;
    protected Canvas FCanvas;
    protected String FName;
    protected Point2D FPosition;
    protected int FPixelSize;
    protected Color FBackgroundColor;
    protected double FWidth;
    protected double FHeight;

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

    public DisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }


    public DisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, new Point2D(0, 0));
    }

    public DisplayController(Canvas aCanvas, String aName, Point2D aPosition) {
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

    }

    public void Initialize() {
        FGC = FCanvas.getGraphicsContext2D();
        InternalUpdate();
    }

    public void Render() {
        DoBeforeRender();
        DoRender();
        DoAfterRender();
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

}
