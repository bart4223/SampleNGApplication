package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGMultiDigitNumberDisplayManager extends NGDisplayManager {

    protected int FDigitCount;
    protected int FMaxCount;
    protected String FDCClassname;

    protected void CreateDigits() {
        for (int i = 0; i < FDigitCount; i++) {
            try {
                NGDisplayController dc = (NGDisplayController)NGDisplayController.class.getClassLoader().loadClass(FDCClassname).getConstructor(Canvas.class, String.class).newInstance(FCanvas, "DIGIT" + (FDigitCount - i - 1));
                addController(dc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void RecalculateDimensions() {
        FWidth = BaseWidth * FPixelSize * FDigitCount;
        FHeight = BaseHeight * FPixelSize;
    }

    @Override
    protected void DoAfterInitialize() {
        for (int i = 0; i < FDigitCount; i++) {
            NGDisplayController dc = getController("DIGIT" + (FDigitCount - i - 1));
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
            NGDisplayController dc = getController("DIGIT" + (FDigitCount - i - 1));
            dc.setProperty(dc, "Number", number);
            dc.setPosition(FPosition.getXAsInt() + i * BaseWidth, FPosition.getYAsInt());
            dc.setBackgroundColor(FBackgroundColor);
            dc.setPixelSize(FPixelSize);
            dc.setProperty(dc, "NumberColor", NumberColor);
            maxcount = maxcount / 10;
        }
    }

    public NGMultiDigitNumberDisplayManager(String aDCClassname, Canvas aCanvas, int aDigitCount) {
        this(aDCClassname, aCanvas, aDigitCount, "");
    }

    public NGMultiDigitNumberDisplayManager(String aDCClassname, Canvas aCanvas, int aDigitCount, String aName) {
        super(aCanvas, aName);
        FDCClassname = aDCClassname;
        FDigitCount = aDigitCount;
        FMaxCount = 1;
        for (int i = 0; i < FDigitCount - 1; i++) {
            FMaxCount = FMaxCount * 10;
        }
        Count = 0;
        NumberColor = Color.BLACK;
        CreateDigits();
    }

    public int Count;
    public Color NumberColor;

    public int getDigitCount() {
        return FDigitCount;
    }

}
