package Uniwork.Misc;

import Uniwork.Base.NGObject;
import javafx.scene.image.Image;

public class NGImageListItem extends NGObject {

    protected String FFilename;
    protected Image FImage;

    public NGImageListItem(String aFilename, Image aImage) {
        super();
        FFilename = aFilename;
        FImage = aImage;
    }

    public String getFilename() {
        return FFilename;
    }

    public Image getImage() {
        return FImage;
    }

}
