package Uniwork.Graphics;

import Uniwork.Base.NGObject;

import java.util.ArrayList;
import java.util.Iterator;

public class NGColorPalette extends NGObject {

    protected ArrayList<NGColorPaletteItem> FItems;

    public NGColorPalette() {
        super();
        FItems = new ArrayList<NGColorPaletteItem>();
    }

    public void addColor(Integer aRed, Integer aGreen, Integer aBlue) {
        NGColorPaletteItem item = new NGColorPaletteItem(aRed, aGreen, aBlue);
        FItems.add(item);
    }

    public Iterator<NGColorPaletteItem> getIterator() {
        return FItems.iterator();
    }

    public void Clear() {
        FItems.clear();
    }

    public Integer getCount() {
        return FItems.size();
    }

}
