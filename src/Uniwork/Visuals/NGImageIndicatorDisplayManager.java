package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGImageIndicatorDisplayManager extends NGDisplayManager {

    protected Integer FIndicatorCount;
    protected String FImageFilename;

    protected void CreateIndicators() {
        for (int i = 0; i < FIndicatorCount; i++) {
            addController(new NGImageDisplayController(FCanvas, String.format("INDICATOR%d", i), FImageFilename));
        }
    }

    @Override
    protected void RecalculateDimensions() {
        FWidth = BaseWidth * FIndicatorCount;
        FHeight = BaseHeight;
    }

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        for (int i = 0; i < FIndicatorCount; i++) {
            NGImageDisplayController dc = (NGImageDisplayController)getController(String.format("INDICATOR%d", i));
            if (i < Indicator) {
                dc.setProperty(dc,String.format("%s.ImageNumber", CLAYERBACKGROUND), HighIndicatorIndex);
            }
            else {
                dc.setProperty(dc,String.format("%s.ImageNumber", CLAYERBACKGROUND), LowIndicatorIndex);
            }
            dc.setPosition(getPositionX() + i * BaseWidth, getPositionY());
        }
    }

    @Override
    protected void DoAfterRender() {
        for (int i = 0; i < FIndicatorCount; i++) {
            NGImageDisplayController dc = (NGImageDisplayController)getController(String.format("INDICATOR%d", i));
            BaseWidth = (int)dc.getWidth();
            BaseHeight = (int)dc.getHeight();
        }
        super.DoAfterRender();
    }

    public NGImageIndicatorDisplayManager(Canvas aCanvas, Integer aIndicatorCount, String aImageFilename) {
        this(aCanvas, aIndicatorCount, aImageFilename, "");
    }

    public NGImageIndicatorDisplayManager(Canvas aCanvas, Integer aIndicatorCount, String aImageFilename, String aName) {
        super(aCanvas, aName);
        FIndicatorCount = aIndicatorCount;
        FImageFilename = aImageFilename;
        Indicator = 0;
        LowIndicatorIndex = 0;
        HighIndicatorIndex = 0;
        CreateIndicators();
    }

    public Integer getIndicatorCount() {
        return FIndicatorCount;
    }

    public Integer Indicator;
    public Integer LowIndicatorIndex;
    public Integer HighIndicatorIndex;

}
