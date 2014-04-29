package Uniwork.Visuals;

import Uniwork.Graphics.NGGeometryObject2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGGeometryObject2DDisplayManager extends NGDisplayManager {

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        if (GeometryObject != null) {
            NGDisplayController dc;
            String classname = "Uniwork.Visuals." + GeometryObject.getClass().getSimpleName() + "DisplayController";
            if (!setCurrentController(classname)) {
                try {
                    dc = (NGGeometryObject2DDisplayController) NGGeometryObject2DDisplayController.class.getClassLoader().loadClass(classname).getConstructor(Canvas.class, String.class).newInstance(FCanvas, classname);
                    dc.Initialize();
                    addController(dc);
                    setCurrentController(dc);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FCurrentController.setPixelSize(getPixelSize());
            setProperty(FCurrentController, "Selected", Selected );
            setProperty(FCurrentController, "GeometryObject", GeometryObject);
            setProperty(FCurrentController, "GeometryObjectColor", GeometryObjectColor);
        }
        else
            FCurrentController = null;
    }

    public NGGeometryObject2DDisplayManager(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public NGGeometryObject2DDisplayManager(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

    public NGGeometryObject2D GeometryObject;
    public Color GeometryObjectColor;
    public boolean Selected;

}
