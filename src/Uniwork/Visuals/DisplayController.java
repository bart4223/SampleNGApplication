package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DisplayController {

    protected GraphicsContext FGC;
    protected Canvas FCanvas;
    protected String FName;

    protected void DoBeforeRender() {

    }

    protected void DoRender() {

    }

    protected void DoAfterRender() {

    }

    public DisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public DisplayController(Canvas aCanvas, String aName) {
        FCanvas = aCanvas;
        FName = aName;
    }

    public void Initialize() {
        FGC = FCanvas.getGraphicsContext2D();
    }

    public void Render() {
        DoBeforeRender();
        DoRender();
        DoAfterRender();
    }

    public String getName() {
        return FName;
    }

}
