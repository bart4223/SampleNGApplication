package Uniwork.Graphics;

import Uniwork.Base.NGObject;
import javafx.scene.paint.Color;

public class NGColorPaletteItem extends NGObject {

    protected Integer FRed;
    protected Integer FGreen;
    protected Integer FBlue;
    protected Color FColor;

    public NGColorPaletteItem(Integer aRed, Integer aGreen, Integer aBlue) {
        super();
        FRed = aRed;
        FGreen = aGreen;
        FBlue = aBlue;
        FColor = Color.rgb(FRed, FGreen, FBlue);
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
        return Math.abs(getRed() - (int)(aColor.getRed() * 255)) + Math.abs(getGreen() - (int)(aColor.getGreen() * 255)) + Math.abs(getBlue() - (int)(aColor.getBlue() * 255));
    }
    
    public Color getColor() {
        return FColor;
    }

}
