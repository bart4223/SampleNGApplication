package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGImageDisplayController extends NGDisplayController{

    @Override
    protected void BeforeRender() {
        super.BeforeRender();
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
        int x = (int)(getPositionX() - getViewPositionX());
        int y = (int)(getPositionY() - getViewPositionY());
        drawImage(x, y, FWidth, FHeight);
    }

    public NGImageDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, "");
    }

    public NGImageDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName);
        setImageName(aImagename);
        ImageScale = 1;
    }

    public double ImageScale;

}
