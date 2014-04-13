package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class SevenSegmentDisplayController extends DisplayController{

    private static final byte[] CNUMBERS = new byte[] { (byte)0X3F, (byte)0X06, (byte)0X5B, (byte)0X4F, (byte)0X66,
            (byte)0X6D, (byte)0X7D, (byte)0X07, (byte)0X7F, (byte)0X6F };

    protected int FNumber;
    protected Color FSegmentColor;

    @Override
    protected void DoRender() {
        byte x = 1;
        byte segment;
        int index = FNumber%10;
        byte number = CNUMBERS[index];
        for (int i = 0; i < 7; i++) {
            segment = (byte)(number&x);
            switch (segment) {
                case 1:
                    drawLine(2, 1, 5, 1, FSegmentColor);
                    break;
                case 2:
                    drawLine(6, 2, 6, 5, FSegmentColor);
                    break;
                case 4:
                    drawLine(6, 7, 6, 10, FSegmentColor);
                    break;
                case 8:
                    drawLine(2, 11, 5, 11, FSegmentColor);
                    break;
                case 16:
                    drawLine(1, 7, 1, 10, FSegmentColor);
                    break;
                case 32:
                    drawLine(1, 2, 1, 5, FSegmentColor);
                    break;
                case 64:
                    drawLine(2, 6, 5, 6, FSegmentColor);
                    break;
            }
            x<<=1;
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

}