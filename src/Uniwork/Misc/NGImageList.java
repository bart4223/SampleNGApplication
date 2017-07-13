package Uniwork.Misc;

import Uniwork.Base.NGObject;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGImageList extends NGObject {

    protected CopyOnWriteArrayList<NGImageListItem> FItems;

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
        FItems = new CopyOnWriteArrayList<NGImageListItem>();
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

    public void reset() {
        FItems.clear();
    }

    public static void resetGlobal() {
        GlobalImages.reset();
    }

    public Image getImage(String aFilename) {
        NGImageListItem item = getItem(aFilename);
        if (item != null) {
            return item.getImage();
        }
        return null;
    }

    public static Image getGlobalImage(String aFilename) {
        NGImageListItem item = GlobalImages.getItem(aFilename);
        if (item == null) {
            try {
                InputStream instream = new FileInputStream(aFilename);
                Image img = new Image(instream);
                GlobalImages.addImage(aFilename, img);
            }
            catch (Exception e) {
                GlobalImages.addImage(aFilename, null);
            }
            item = GlobalImages.getItem(aFilename);
        }
        return item.getImage();
    }

}
