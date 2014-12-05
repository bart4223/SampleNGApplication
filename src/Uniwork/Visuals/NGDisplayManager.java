package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class NGDisplayManager extends NGDisplayController {

    protected ArrayList<NGDisplayController> FControllers;
    protected Canvas FCanvas;
    protected NGDisplayController FCurrentController;

    protected void DoInitializeController(NGDisplayController aController) {
       aController.Initialize();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        for (NGDisplayController controller : FControllers) {
            DoInitializeController(controller);
        }
    }

    protected void DoRenderController(NGDisplayController aController) {
       aController.Render();
    }

    @Override
    protected void DoRender() {
        super.DoRender();
        if (FCurrentController == null) {
            for (NGDisplayController controller : FControllers) {
                    DoRenderController(controller);
            }
        }
        else {
            FCurrentController.Render();
        }
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            NGDisplayController dc = getController(name);
            return dc.getProperty(dc, aName.substring(index + 1, aName.length()));
        }
        else {
            return super.getProperty(aObject, aName);
        }
    }

    @Override
    public Boolean setProperty(Object aObject, String aName, Object aValue) {
        Boolean res;
        Integer index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            NGDisplayController dc = getController(name);
            res = dc.setProperty(dc, aName.substring(index + 1, aName.length()), aValue);
        }
        else {
            res = super.setProperty(aObject, aName, aValue);
        }
        return res;
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
        FCurrentController = null;
    }

    public void addController(NGDisplayController aController) {
        aController.setView(getView());
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

    public NGDisplayController getCurrentController() {
        return FCurrentController;
    }

    public ArrayList<NGDisplayController> getControllers() {
        return FControllers;
    }

    public Boolean setCurrentController(String aName) {
        NGDisplayController dc = getController(aName);
        setCurrentController(dc);
        return dc != null;
    }

    public void setCurrentController(NGDisplayController aController) {
        FCurrentController = aController;
    }

    public void resetCurrentController() {
        FCurrentController = null;
    }

    @Override
    public void setView(NGDisplayView aView) {
        super.setView(aView);
        for (NGDisplayController Controller : FControllers) {
            Controller.setView(aView);
        }
    }

}
