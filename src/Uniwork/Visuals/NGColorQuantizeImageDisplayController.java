package Uniwork.Visuals;

import Uniwork.Graphics.NGColorOctree;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public class NGColorQuantizeImageDisplayController extends NGImageDisplayController {

    protected BufferedImage FImage;
    protected NGColorOctree FColorOctree;

    @Override
    protected void DoRender() {
        double x = getPositionX() - getViewPositionX();
        double y = getPositionY() - getViewPositionY();
        for (int yy = (int)x; yy < (int)y + FImage.getHeight(); yy++) {
            for (int xx = (int)y; xx < (int)x + FImage.getWidth(); xx++) {
                int color = FImage.getRGB(xx, yy);
                int  red   = (color & 0x00ff0000) >> 16;
                int  green = (color & 0x0000ff00) >> 8;
                int  blue  =  color & 0x000000ff;
                drawPixel(xx, yy, FColorOctree.getNodeColorFromColor(Color.rgb(red, green, blue)));
            }
        }
    }

    public NGColorQuantizeImageDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, null);
    }

    public NGColorQuantizeImageDisplayController (Canvas aCanvas, String aName, BufferedImage aImage) {
        super(aCanvas, aName, "");
        FImage = aImage;
        FColorOctree = null;
    }

    public void setImage(BufferedImage aImage, NGColorOctree aColorOctree) {
        FImage = aImage;
        FColorOctree = aColorOctree;
    }

}
