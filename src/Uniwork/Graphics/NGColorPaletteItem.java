package Uniwork.Graphics;

import Uniwork.Base.NGObject;

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


}
