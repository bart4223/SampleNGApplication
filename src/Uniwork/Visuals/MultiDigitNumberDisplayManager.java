package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class MultiDigitNumberDisplayManager extends DisplayManager{

    protected int FDigitCount;
    protected int FMaxCount;
    protected String FDCClassname;

    @Override
    protected void RecalculateDimensions() {
        FWidth = BaseWidth * FPixelSize * FDigitCount;
        FHeight = BaseHeight * FPixelSize;
    }

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        for (int i = 0; i < FDigitCount; i++) {
            try {
                DisplayController dc = (DisplayController)DisplayController.class.getClassLoader().loadClass(FDCClassname).getConstructor(Canvas.class, String.class).newInstance(FCanvas, "DIGIT" + (FDigitCount - i - 1));
                addController(dc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void DoAfterInitialize() {
        for (int i = 0; i < FDigitCount; i++) {
            DisplayController dc = getController("DIGIT" + (FDigitCount - i - 1));
            BaseWidth = (Integer)dc.getProperty(dc, "BaseWidth");
            BaseHeight = (Integer)dc.getProperty(dc, "BaseHeight");
        }
        super.DoAfterInitialize();
    }

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        int number;
        int count = Count % (FMaxCount * 10);
        int maxcount = FMaxCount;
        for (int i = 0; i < FDigitCount; i++) {
            if (maxcount >= 10 )
                number = count / maxcount;
            else
                number = count % 10;
            DisplayController dc = getController("DIGIT" + (FDigitCount - i - 1));
            dc.setProperty(dc, "Number", number);
            dc.setPosition(FPosition.getXAsInt() + i * BaseWidth, FPosition.getYAsInt());
            dc.setBackgroundColor(FBackgroundColor);
            dc.setPixelSize(FPixelSize);
            dc.setProperty(dc, "NumberColor", NumberColor);
            maxcount = maxcount / 10;
        }
    }

    public MultiDigitNumberDisplayManager(String aDCClassname, Canvas aCanvas, int aDigitCount) {
        this(aDCClassname, aCanvas, aDigitCount, "");
    }

    public MultiDigitNumberDisplayManager(String aDCClassname, Canvas aCanvas, int aDigitCount, String aName) {
        super(aCanvas, aName);
        FDCClassname = aDCClassname;
        FDigitCount = aDigitCount;
        FMaxCount = 1;
        for (int i = 0; i < FDigitCount - 1; i++) {
            FMaxCount = FMaxCount * 10;
        }
        Count = 0;
        NumberColor = Color.BLACK;
    }

    public int Count;
    public Color NumberColor;

    public int getDigitCount() {
        return FDigitCount;
    }

}
