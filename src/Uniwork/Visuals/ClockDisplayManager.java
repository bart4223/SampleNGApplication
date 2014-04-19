package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ClockDisplayManager extends DisplayManager {

    protected String FDCClassname;

    @Override
    protected void RecalculateDimensions() {
        FWidth = BaseWidth * FPixelSize * 3 + 3 * FPixelSize;
        FHeight = BaseHeight * FPixelSize;
    }

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        FGC.setFill(FBackgroundColor);
        FGC.fillRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
    }

    @Override
    protected void DoInitialize() {
        DisplayController dc;
        try {
            dc = new MultiDigitNumberDisplayManager(FDCClassname, FCanvas, 2);
            dc.setName("HOURS");
            dc.setPosition(FPosition.getXAsInt(), FPosition.getYAsInt());
            dc.setBackgroundColor(FBackgroundColor);
            dc.setPixelSize(FPixelSize);
            dc.Initialize();
            BaseWidth = dc.BaseWidth * 2;
            BaseHeight = dc.BaseHeight;
            addController(dc);
            dc = new MultiDigitNumberDisplayManager(FDCClassname, FCanvas, 2);
            dc.setName("MINUTES");
            dc.setPosition(FPosition.getXAsInt() + BaseWidth + FPixelSize, FPosition.getYAsInt());
            dc.setBackgroundColor(FBackgroundColor);
            dc.setPixelSize(FPixelSize);
            addController(dc);
            dc = new MultiDigitNumberDisplayManager(FDCClassname, FCanvas, 2);
            dc.setName("SECONDS");
            dc.setPosition(FPosition.getXAsInt() + 2 * BaseWidth + 2 * FPixelSize, FPosition.getYAsInt());
            dc.setBackgroundColor(FBackgroundColor);
            dc.setPixelSize(FPixelSize);
            addController(dc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.DoInitialize();
    }

    @Override
    protected void DoRender() {
        Calendar calender = Calendar.getInstance();
        calender.setTime(Date);
        calender.setTimeZone(TimeZone);
        DisplayController dc = getController("HOURS");
        dc.setProperty(dc, "Count", calender.get(Calendar.HOUR_OF_DAY));
        dc.setProperty(dc, "NumberColor", NumberColor);
        dc = getController("MINUTES");
        dc.setProperty(dc, "Count", calender.get(Calendar.MINUTE));
        dc.setProperty(dc, "NumberColor", NumberColor);
        dc = getController("SECONDS");
        dc.setProperty(dc, "Count", calender.get(Calendar.SECOND));
        dc.setProperty(dc, "NumberColor", NumberColor);
        drawPixel(FPosition.getXAsInt() + BaseWidth + FPixelSize / 2 - 1, FPosition.getYAsInt() + BaseHeight / 3, NumberColor);
        drawPixel(FPosition.getXAsInt() + BaseWidth + FPixelSize / 2 - 1, FPosition.getYAsInt() + BaseHeight * 2 / 3, NumberColor);
        drawPixel(FPosition.getXAsInt() + 2 * BaseWidth + FPixelSize * 3 / 2 - 1, FPosition.getYAsInt() + BaseHeight / 3, NumberColor);
        drawPixel(FPosition.getXAsInt() + 2 * BaseWidth + FPixelSize * 3 / 2 - 1, FPosition.getYAsInt() + BaseHeight * 2 / 3, NumberColor);
        super.DoRender();
    }

    public ClockDisplayManager(String aDCClassname, Canvas aCanvas) {
        super(aCanvas);
        FDCClassname = aDCClassname;
        NumberColor = Color.BLACK;
        Date = new Date();
        TimeZone = TimeZone.getTimeZone("UTC");
    }

    public Date Date;
    public TimeZone TimeZone;
    public Color NumberColor;

}
