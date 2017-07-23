package Uniwork.Graphics;

import Uniwork.Base.NGObject;
import javafx.scene.paint.Color;

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

    public Color getNearestColor(Color aColor) {
        Integer dist = null;
        NGColorPaletteItem res = null;
        for (NGColorPaletteItem item : FItems) {
            if (dist == null) {
                dist = item.Distance(aColor);
                res = item;
            } else {
                Integer d = item.Distance(aColor);
                if (d < dist) {
                    dist = d;
                    res = item;
                }
            }
        }
        if (res != null)
            return res.getColor();
        else
            return null;
    }

}
