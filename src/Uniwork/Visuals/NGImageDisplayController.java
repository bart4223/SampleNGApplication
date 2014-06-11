package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGImageDisplayController extends NGDisplayController{

    protected String FSaveImageName;

    protected Boolean IsClipRect() {
        double x = getPositionX() - getViewPositionX();
        double y = getPositionY() - getViewPositionY();
        return (x > FCanvas.getWidth()) || (y > FCanvas.getHeight()) || (x + FWidth < 0) || (y + FHeight < 0);
    }

    @Override
    public void setImageName(String aImageName) {
        super.setImageName(aImageName);
        FSaveImageName = aImageName;
    }

    @Override
    protected void BeforeRender() {
        super.BeforeRender();
        if (ImageNumber >= 0) {
            FImageName = String.format(FSaveImageName, ImageNumber%MaxImageNumber);
        }
        double x = getPositionX() - getViewPositionX() + 1;
        double y = getPositionY() - getViewPositionY() + 1;
        FGC.clearRect(x, y, FWidth, FHeight);
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
    public int ImageNumber;
    public int MaxImageNumber;

}
