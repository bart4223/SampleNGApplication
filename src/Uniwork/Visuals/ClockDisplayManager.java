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
        if (Separator) {
            FWidth = BaseWidth * FPixelSize * 3 + 3 * FPixelSize;
        }
        else {
            FWidth = BaseWidth * FPixelSize * 3;
        }
        FHeight = BaseHeight * FPixelSize;
    }

    protected void CreateParts() {
        try {
            DisplayController dc = new MultiDigitNumberDisplayManager(FDCClassname, FCanvas, 2, "HOURS");
            addController(dc);
            dc = new MultiDigitNumberDisplayManager(FDCClassname, FCanvas, 2, "MINUTES");
            addController(dc);
            dc = new MultiDigitNumberDisplayManager(FDCClassname, FCanvas, 2, "SECONDS");
            addController(dc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void DoAfterInitialize() {
        DisplayController dc = getController("HOURS");
        BaseWidth = dc.BaseWidth * 2;
        BaseHeight = dc.BaseHeight;
        super.DoAfterInitialize();
    }

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        Calendar calender = Calendar.getInstance();
        calender.setTime(Date);
        calender.setTimeZone(TimeZone);
        DisplayController dc = getController("HOURS");
        dc.setPosition(FPosition.getXAsInt(), FPosition.getYAsInt());
        dc.setBackgroundColor(FBackgroundColor);
        dc.setPixelSize(FPixelSize);
        dc.setProperty(dc, "NumberColor", HoursColor);
        dc.setProperty(dc, "Count", calender.get(Calendar.HOUR_OF_DAY));
        dc = getController("MINUTES");
        if (Separator) {
            dc.setPosition(FPosition.getXAsInt() + BaseWidth + FPixelSize, FPosition.getYAsInt());
        }
        else {
            dc.setPosition(FPosition.getXAsInt() + BaseWidth, FPosition.getYAsInt());
        }
        dc.setBackgroundColor(FBackgroundColor);
        dc.setPixelSize(FPixelSize);
        dc.setProperty(dc, "NumberColor", MinutesColor);
        dc.setProperty(dc, "Count", calender.get(Calendar.MINUTE));
        dc = getController("SECONDS");
        if (Separator) {
            dc.setPosition(FPosition.getXAsInt() + 2 * BaseWidth + 2 * FPixelSize, FPosition.getYAsInt());
        }
        else {
            dc.setPosition(FPosition.getXAsInt() + 2 * BaseWidth, FPosition.getYAsInt());
        }
        dc.setBackgroundColor(FBackgroundColor);
        dc.setPixelSize(FPixelSize);
        dc.setProperty(dc, "NumberColor", SecondsColor);
        dc.setProperty(dc, "Count", calender.get(Calendar.SECOND));
    }

    @Override
    protected void DoRender() {
        FGC.setFill(FBackgroundColor);
        FGC.fillRect(FPosition.getXAsInt() * FPixelSize, FPosition.getYAsInt() * FPixelSize, FWidth, FHeight);
        super.DoRender();
        if (Separator) {
            drawPixel(FPosition.getXAsInt() + BaseWidth + FPixelSize / 2 - 1, FPosition.getYAsInt() + BaseHeight / 3, SeparatorColor);
            drawPixel(FPosition.getXAsInt() + BaseWidth + FPixelSize / 2 - 1, FPosition.getYAsInt() + BaseHeight * 2 / 3, SeparatorColor);
            drawPixel(FPosition.getXAsInt() + 2 * BaseWidth + FPixelSize * 3 / 2 - 1, FPosition.getYAsInt() + BaseHeight / 3, SeparatorColor);
            drawPixel(FPosition.getXAsInt() + 2 * BaseWidth + FPixelSize * 3 / 2 - 1, FPosition.getYAsInt() + BaseHeight * 2 / 3, SeparatorColor);
        }
    }

    public ClockDisplayManager(String aDCClassname, Canvas aCanvas) {
        super(aCanvas);
        FDCClassname = aDCClassname;
        HoursColor = Color.BLACK;
        MinutesColor = Color.BLACK;
        SecondsColor = Color.BLACK;
        SeparatorColor = Color.BLACK;
        Date = new Date();
        TimeZone = TimeZone.getTimeZone("UTC");
        Separator = true;
        CreateParts();
    }

    public Date Date;
    public TimeZone TimeZone;
    public Color HoursColor;
    public Color MinutesColor;
    public Color SecondsColor;
    public Color SeparatorColor;
    public boolean Separator;

}
