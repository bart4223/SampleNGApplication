package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGImageDisplayController extends NGDisplayController{

    protected String FSaveImageName;

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
        obtainImage();
        FGC.clearRect(FPosition.getX(), FPosition.getY(), FWidth, FHeight);
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
        double x = getPositionX() - getViewPositionX();
        double y = getPositionY() - getViewPositionY();
        drawImage(x, y, FWidth, FHeight);
    }

    public NGImageDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, "");
    }

    public NGImageDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName);
        setImageName(aImagename);
        FSaveImageName = "";
        ImageScale = 1;
        ImageNumber = -1;
        MaxImageNumber = 10;
    }

    public double ImageScale;
    public int ImageNumber;
    public int MaxImageNumber;

}
