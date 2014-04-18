package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class DisplayManager extends DisplayController{

    protected ArrayList<DisplayController> FControllers;
    protected Canvas FCanvas;

    protected void DoInitialize() {
        for (DisplayController Controller : FControllers) {
            Controller.Initialize();
        }
    }

    protected void DoRender() {
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

    public void Initialize() {
        DoInitialize();
    }

    public void addController(DisplayController aController) {
        FControllers.add(aController);
    }

    public void removeController(DisplayController aController) {
        FControllers.remove(aController);
    }

    public void Render() {
        DoRender();
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
