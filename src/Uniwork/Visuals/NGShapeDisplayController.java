package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGShapeDisplayController extends NGDisplayController {

    protected byte[] getSubByteArrayFrom(byte[] aArray, int aSizeX, int aSizeY, int aPosition) {
        byte[] array = new byte[aSizeX * aSizeY];
        for (int i = 0; i < array.length; i++) {
            array[i] = aArray[aPosition * array.length + i];
        }
        return array;
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
                    else {
                        drawPixel(x + i + FPosition.getXAsInt(), y + FPosition.getYAsInt(), FBackgroundColor);
                    }
                    ii>>=1;
                }
            }
            y++;
        }
    }

    public NGShapeDisplayController(Canvas aCanvas) {
        super(aCanvas);
    }

    public NGShapeDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

}
