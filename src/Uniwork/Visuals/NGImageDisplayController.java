package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

public class NGImageDisplayController extends NGDisplayController{

    @Override
    protected void RecalculateDimensions() {
        FWidth = BaseWidth;
        FHeight = BaseHeight;
    }

    @Override
    protected void DoRender() {

    }

    public NGImageDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        BaseWidth = 8;
        BaseHeight = 8;
        Filename = "";
        // ToDo statische Imageliste
    }

    public String Filename;

}
