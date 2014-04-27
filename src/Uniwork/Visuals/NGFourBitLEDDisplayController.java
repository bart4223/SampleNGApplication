package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGFourBitLEDDisplayController extends NGDisplayController {

    @Override
    protected void RecalculateDimensions() {
        BaseWidth = 2 + 2 * BitSize;
        BaseHeight = 8 + 4 * 2 * BitSize;
        FWidth = BaseWidth * FPixelSize;
        FHeight = BaseHeight * FPixelSize;
    }

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.setFill(FBackgroundColor);
        FGC.fillRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoRender() {
        int x = FPosition.getXAsInt();
        int y = FPosition.getYAsInt();
        for (int i = 1; i < 16; i<<=1) {
            if (i > MaxNumber)
                break;
            int res = Number&i;
            Color color = FBackgroundColor.darker();
            if (res != 0)
                color = NumberColor;
            switch (i) {
                case 1:
                    fillCircle(x + 1 + BitSize, y + 7 + 7 * BitSize, BitSize, color);
                    break;
                case 2:
                    fillCircle(x + 1 + BitSize, y + 5 + 5 * BitSize, BitSize, color);
                    break;
                case 4:
                    fillCircle(x + 1 + BitSize, y + 3 + 3 * BitSize, BitSize, color);
                    break;
                case 8:
                    fillCircle(x + 1 + BitSize, y + 1 + 1 * BitSize, BitSize, color);
                    break;
            }
        }
    }

    public NGFourBitLEDDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public NGFourBitLEDDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        BaseWidth = 2;
        BaseHeight = 8;
        MaxNumber = 16;
        BitSize = 1;
        NumberColor = Color.RED;
        Number = 0;
    }

    public int BitSize;
    public int Number;
    public Color NumberColor;
    public int MaxNumber;

}
