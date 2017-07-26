package Uniwork.Visuals;

import Uniwork.Misc.NGStrings;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Hashtable;

public class NGLabeledGrid2DDisplayController extends NGGrid2DDisplayController {

    protected Hashtable<String, String> FLabels;

    @Override
    protected void DoRender() {
        super.DoRender();
        if (!DrawGrid) return;
        Font font = new Font(FontName, FontSize);
        FGC.setStroke(FontColor);
        FGC.setFont(font);
        double x = 0 - getViewPositionX();
        double y = 0 - getViewPositionY();
        Integer indexX;
        Integer indexY = 0;
        for(double yy = y; yy < y + GridHeight; yy = yy + GridDistance) {
            indexX = 0;
            for(double xx = x; xx <= x + GridWidth; xx = xx + GridDistance) {
                String label = FLabels.get(String.format("%d.%d", indexY, indexX));
                if (label != null) {
                    String l = label;
                    if (NGStrings.getStringCount(l,"") == 2) {
                        l = NGStrings.getStringPos(label, "", 1);
                        String c = NGStrings.getStringPos(label, "", 2);
                        FGC.setStroke(Color.web(c));
                    }
                    FGC.strokeText(l, xx + 3, yy + GridDistance - FontSize / 3);
                }
                indexX = indexX + 1;
            }
            indexY = indexY + 1;
        }
    }

    public NGLabeledGrid2DDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public NGLabeledGrid2DDisplayController(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        FLabels = new Hashtable<String, String>();
        FontSize = 14;
        FontName = "Courier";
        FontColor = Color.BLACK;
    }

    public Integer FontSize;
    public String FontName;
    public Color FontColor;

    public void clearLabels() {
        FLabels.clear();
    }

    public void addLabel(String aIndex, String aLabel) {
        FLabels.put(aIndex, aLabel);
    }

}
