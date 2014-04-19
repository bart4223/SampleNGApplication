package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class DisplayManager extends DisplayController{

    protected ArrayList<DisplayController> FControllers;
    protected Canvas FCanvas;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        for (DisplayController Controller : FControllers) {
            Controller.Initialize();
        }
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        for (DisplayController Controller : FControllers) {
            Controller.Render();
        }
    }

    public DisplayManager() {
        this(null);
    }

    public DisplayManager(Canvas aCanvas) {
        super(aCanvas);
        FControllers = new ArrayList<DisplayController>();
        FCanvas = aCanvas;
    }

    public void addController(DisplayController aController) {
        FControllers.add(aController);
    }

    public void removeController(DisplayController aController) {
        FControllers.remove(aController);
    }

    public DisplayController getController(String aName) {
        for (DisplayController Controller : FControllers) {
            if (Controller.getName().equals(aName)) {
                return Controller;
            }
        }
        return null;
    }

}
