package Uniwork.Graphics;

import Uniwork.Base.NGObject;
import javafx.scene.paint.Color;

public class NGColorPaletteItem extends NGObject {

    protected Integer FRed;
    protected Integer FGreen;
    protected Integer FBlue;

    public NGColorPaletteItem(Integer aRed, Integer aGreen, Integer aBlue) {
        super();
        FRed = aRed;
        FGreen = aGreen;
        FBlue = aBlue;
    }

    public Integer getRed() {
        return FRed;
    }

    public Integer getGreen() {
        return FGreen;
    }

    public Integer getBlue() {
        return FBlue;
    }

    public Integer Distance(Color aColor) {
        return Math.abs(getRed() - (int)aColor.getRed() + getGreen() - (int)aColor.getGreen() + getBlue() - (int)aColor.getBlue());
    }
    
    public Color getColor() {
        return new Color(getRed()/100, getGreen()/100, getBlue()/100, 1.0);
    }

}
