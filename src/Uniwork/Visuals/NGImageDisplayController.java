package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGImageDisplayController extends NGDisplayController{

    protected String FSaveImageName;

    protected Boolean IsClipRect() {
        double x = getPositionX() - getViewPositionX();
        double y = getPositionY() - getViewPositionY();
        return (x > getViewWidth()) || (y > getViewHeight()) || (x + FWidth < 0) || (y + FHeight < 0);
    }

    @Override
    public void setImageName(String aImageName) {
        super.setImageName(aImageName);
        FSaveImageName = aImageName;
    }

    @Override
    protected void BeforeRender() {
        super.BeforeRender();
        FImageName = FSaveImageName;
        if (MaxImageNumber > 0) {
            FImageName = String.format(FSaveImageName, ImageNumber%MaxImageNumber);
        }
        else if (ImageNumber >= 0) {
            FImageName = String.format(FSaveImageName, ImageNumber);
        }
        double x = getPositionX() - getViewPositionX() + 1;
        double y = getPositionY() - getViewPositionY() + 1;
        FGC.clearRect(x, y, FWidth - 1, FHeight - 1);
        obtainImage();
    }

    @Override
    protected void RecalculateDimensions() {
        if (FImage != null) {
            FWidth = FImage.getWidth() * ImageScale;
            FHeight = FImage.getHeight() * ImageScale;
        }
    }

    @Override
    protected void DoRender() {
        if (!IsClipRect()) {
            double x = getPositionX() - getViewPositionX();
            double y = getPositionY() - getViewPositionY();
            drawImage(x, y, FWidth, FHeight);
        }
    }

    public NGImageDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, "");
    }

    public NGImageDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName);
        setImageName(aImagename);
        FSaveImageName = "";
        ImageScale = 1.0;
        ImageNumber = -1;
        MaxImageNumber = 10;
    }

    public double ImageScale;
    public Integer ImageNumber;
    public Integer MaxImageNumber;

}
