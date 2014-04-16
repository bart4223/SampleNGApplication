package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class SevenSegmentDisplayController extends ShapeDisplayController{

    private static final byte[] CSYMBOLS = new byte[] { (byte)0X3F, (byte)0X06, (byte)0X5B, (byte)0X4F, (byte)0X66,
            (byte)0X6D, (byte)0X7D, (byte)0X07, (byte)0X7F, (byte)0X6F };

    protected int FNumber;
    protected Color FSegmentColor;
    protected boolean FDecimalPoint;

    @Override
    protected void DoRender() {
        int x = FPosition.getXAsInt();
        int y = FPosition.getYAsInt();
        byte symbol = CSYMBOLS[FNumber%10];
        for (int i = 1; i < 128; i<<=1) {
            switch (symbol&i) {
                case 1:
                    drawLine(x + 2, y + 1, x + 5, y + 1, FSegmentColor);
                    break;
                case 2:
                    drawLine(x + 6, y + 2, x + 6, y + 5, FSegmentColor);
                    break;
                case 4:
                    drawLine(x + 6, y + 7, x + 6, y + 10, FSegmentColor);
                    break;
                case 8:
                    drawLine(x + 2, y + 11, x + 5, y + 11, FSegmentColor);
                    break;
                case 16:
                    drawLine(x + 1, y + 7, x + 1, y + 10, FSegmentColor);
                    break;
                case 32:
                    drawLine(x + 1, y + 2, x + 1, y + 5, FSegmentColor);
                    break;
                case 64:
                    drawLine(x + 2, y + 6, x + 5, y + 6, FSegmentColor);
                    break;
            }
        }
        if (FDecimalPoint) {
            drawPixel(x + 8, y + 11, FSegmentColor);
        }
    }

    public SevenSegmentDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public SevenSegmentDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        FSegmentColor = Color.BLACK;
        FNumber = 0;
        FPixelSize = 1;
        FDecimalPoint = false;
    }

    public void setNumber(int aValue) {
        FNumber = aValue;
    }

    public int getNumber() {
        return FNumber;
    }

    public void setSegmentColor(Color aValue) {
        FSegmentColor = aValue;
    }

    public Color getSegmentColor() {
        return FSegmentColor;
    }

    public boolean getDecimalPoint() {
        return FDecimalPoint;
    }

    public void setDecimalPoint(boolean aValue) {
        FDecimalPoint = aValue;
    }

}