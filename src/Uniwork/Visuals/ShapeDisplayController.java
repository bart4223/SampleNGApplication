package Uniwork.Visuals;

import Uniwork.Graphics.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;

public class ShapeDisplayController extends DisplayController {

    protected double FWidth;
    protected double FHeight;
    protected Point2D FPosition;
    protected Color FBackgroundColor;
    protected int FPixelSize;

    protected byte[] getSubByteArrayFrom(byte[] aArray, int aSizeX, int aSizeY, int aPosition) {
        byte[] array = new byte[aSizeX * aSizeY];
        for (int i = 0; i < array.length; i++) {
            array[i] = aArray[aPosition * array.length + i];
        }
        return array;
    }

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

    protected void drawByteArray(byte[] aArray, int aRange, Color aColor) {
        int y = 0;
        while (y + aRange <= aArray.length) {
            for (int x = 0; x < aRange; x++) {
                byte symbol = aArray[y + x];
                int ii = 128;
                for (int i = 0; i < 8; i++) {
                    int res = symbol&ii;
                    if (res != 0) {
                        drawPixel(x + i + FPosition.getXAsInt(), y + FPosition.getYAsInt(), aColor);
                    }
                    ii>>=1;
                }
            }
            y++;
        }
    }

    @Override
    protected void DoBeforeRender() {
        FGC.setFill(FBackgroundColor);
        FGC.fillRect(0, 0, FWidth, FHeight);
    }

    public ShapeDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public ShapeDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, new Point2D(0, 0));
    }

    public ShapeDisplayController(Canvas aCanvas, String aName, Point2D aPosition) {
        super(aCanvas, aName);
        FWidth = aCanvas.getWidth();
        FHeight = aCanvas.getHeight();
        FPosition = aPosition;
        FBackgroundColor = Color.WHITE;
        FPixelSize = 1;
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

    public Point2D getPosition() {
        return FPosition;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

}
