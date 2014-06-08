package Uniwork.Misc;

import Uniwork.Base.NGObject;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class NGImageList extends NGObject {

    protected ArrayList<NGImageListItem> FItems;

    protected NGImageListItem getItem(String aFilename) {
        for (NGImageListItem item : FItems) {
            if (item.getFilename().equals(aFilename)) {
                return item;
            }
        }
        return null;
    }

    public static NGImageList GlobalImages = new NGImageList();

    public NGImageList() {
        super();
        FItems = new ArrayList<NGImageListItem>();
    }

    public void addImage(String aFilename, Image aImage) {
        NGImageListItem item = new NGImageListItem(aFilename, aImage);
        FItems.add(item);
    }

    public static void addGlobalImage(String aFilename, Image aImage) {
        GlobalImages.addImage(aFilename, aImage);
    }

    public void removeImage(String aFilename) {
        NGImageListItem item = getItem(aFilename);
        if (item != null) {
            FItems.remove(item);
        }
    }

    public static void removeGlobalImage(String aFilename) {
        GlobalImages.removeImage(aFilename);
    }

    public void clear() {
        FItems.clear();
    }

    public static void clearGlobal() {
        GlobalImages.clear();
    }

    public Image getImage(String aFilename) {
        NGImageListItem item = getItem(aFilename);
        if (item != null) {
            return item.getImage();
        }
        return null;
    }

    public static Image getGlobalImage(String aFilename) {
        return GlobalImages.getImage(aFilename);
    }

}
