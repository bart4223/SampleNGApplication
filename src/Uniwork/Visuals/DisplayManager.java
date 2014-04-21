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

    @Override
    public java.lang.Object getProperty(java.lang.Object aObject, String aName) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            DisplayController dc = getController(name);
            return dc.getProperty(dc, aName.substring(index + 1, aName.length()));
        }
        else
            return super.getProperty(aObject, aName);
    }


    @Override
    public void setProperty(java.lang.Object aObject, String aName, java.lang.Object aValue) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            DisplayController dc = getController(name);
            dc.setProperty(dc, aName.substring(index + 1, aName.length()), aValue);
        }
        else
            super.setProperty(aObject, aName, aValue);
    }

    public DisplayManager() {
        this(null);
    }

    public DisplayManager(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public DisplayManager(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
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
