package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class NGDisplayManager extends NGDisplayController {

    protected ArrayList<NGDisplayController> FControllers;
    protected Canvas FCanvas;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        for (NGDisplayController Controller : FControllers) {
            Controller.Initialize();
        }
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        for (NGDisplayController Controller : FControllers) {
            Controller.Render();
        }
    }

    @Override
    public java.lang.Object getProperty(java.lang.Object aObject, String aName) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            NGDisplayController dc = getController(name);
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
            NGDisplayController dc = getController(name);
            dc.setProperty(dc, aName.substring(index + 1, aName.length()), aValue);
        }
        else
            super.setProperty(aObject, aName, aValue);
    }

    public NGDisplayManager() {
        this(null);
    }

    public NGDisplayManager(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public NGDisplayManager(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        FControllers = new ArrayList<NGDisplayController>();
        FCanvas = aCanvas;
    }

    public void addController(NGDisplayController aController) {
        FControllers.add(aController);
    }

    public void removeController(NGDisplayController aController) {
        FControllers.remove(aController);
    }

    public NGDisplayController getController(String aName) {
        for (NGDisplayController Controller : FControllers) {
            if (Controller.getName().equals(aName)) {
                return Controller;
            }
        }
        return null;
    }

}
