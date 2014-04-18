package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class MultiDigitNumberDisplayManager extends DisplayManager{

    protected int FDigitCount;
    protected int FMaxCount;
    protected int FCount;
    protected String FDCClassname;
    protected Color FNumberColor;

    @Override
    protected void DoInitialize() {
        for (int i = 0; i < FDigitCount; i++) {
            try {
                DisplayController dc = (DisplayController)DisplayController.class.getClassLoader().loadClass(FDCClassname).getConstructor(Canvas.class, String.class).newInstance(FCanvas, "DIGIT" + (FDigitCount - i - 1));
                dc.setPosition(FPosition.getXAsInt() + i * 8, FPosition.getYAsInt());
                dc.setBackgroundColor(FBackgroundColor);
                dc.setPixelSize(FPixelSize);
                addController(dc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.DoInitialize();
    }

    @Override
    protected void DoRender() {
        int number;
        int count = FCount % (FMaxCount * 10);
        int maxcount = FMaxCount;
        for (int i = 0; i < FDigitCount; i++) {
            if (maxcount >= 10 )
                number = count / maxcount;
            else
                number = count % 10;
            DisplayController dc = getController("DIGIT" + (FDigitCount - i - 1));
            dc.setProperty(dc, "Number", number);
            dc.setProperty(dc, "NumberColor", FNumberColor);
            maxcount = maxcount / 10;
        }
        super.DoRender();
    }

    public MultiDigitNumberDisplayManager(String aDCClassname, Canvas aCanvas, int aDigitCount) {
        super(aCanvas);
        FDCClassname = aDCClassname;
        FDigitCount = aDigitCount;
        FMaxCount = 1;
        for (int i = 0; i < FDigitCount - 1; i++) {
            FMaxCount = FMaxCount * 10;
        }
        FCount = 0;
        FNumberColor = Color.BLACK;
    }

    public int getDigitCount() {
        return FDigitCount;
    }

    public void setCount(int aValue) {
        FCount = aValue;
    }

    public int getCount() {
        return FCount;
    }

    public void setNumberColor(Color aValue) {
        FNumberColor = aValue;
    }

    public Color getNumberColor() {
        return FNumberColor;
    }

}
