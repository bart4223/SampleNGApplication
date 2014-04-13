package Uniwork.Visuals;

import java.util.ArrayList;

public class DisplayManager {

    protected ArrayList<DisplayController> FControllers;

    public DisplayManager() {
        FControllers = new ArrayList<DisplayController>();
    }

    public void Initialize() {
        for (DisplayController Controller : FControllers) {
            Controller.Initialize();
        }
    }

    public void addController(DisplayController aController) {
        FControllers.add(aController);
    }

    public void removeController(DisplayController aController) {
        FControllers.remove(aController);
    }

    public void Render() {
        for (DisplayController Controller : FControllers) {
            Controller.Render();
        }
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
