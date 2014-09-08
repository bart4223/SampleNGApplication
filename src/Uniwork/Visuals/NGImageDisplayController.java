package Uniwork.Visuals;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;

public class NGImageDisplayController extends NGDisplayController {

    protected ArrayList<NGImageDisplayControllerLayerItem> FLayers;
    protected NGImageDisplayControllerLayerItem FCurrentLayer;

    protected Boolean IsClipRect() {
        double x = getPositionX() - getViewPositionX();
        double y = getPositionY() - getViewPositionY();
        return (x > getViewWidth()) || (y > getViewHeight()) || (x + FWidth < 0) || (y + FHeight < 0);
    }

    @Override
    protected void BeforeRender() {
        super.BeforeRender();
        FImageName = FCurrentLayer.getImageName();
        if (MaxImageNumber > 0) {
            FImageName = String.format(FCurrentLayer.getImageName(), ImageNumber%MaxImageNumber);
        }
        else if (ImageNumber >= 0) {
            FImageName = String.format(FCurrentLayer.getImageName(), ImageNumber);
        }
        if (FCurrentLayer.equals(FLayers.get(0))) {
            double x = getPositionX() - getViewPositionX() + 1;
            double y = getPositionY() - getViewPositionY() + 1;
            FGC.clearRect(x, y, FWidth - 1, FHeight - 1);
        }
        obtainImage();
    }

    @Override
    protected void RecalculateDimensions() {
        if (FImage != null) {
            FWidth = FImage.getWidth() * ImageScale;
            FHeight = FImage.getHeight() * ImageScale;
        }
    }

    @Override
    protected void DoRender() {
        if (!IsClipRect()) {
            double x = getPositionX() - getViewPositionX();
            double y = getPositionY() - getViewPositionY();
            drawImage(x, y, FWidth, FHeight);
        }
    }

    @Override
    protected void InternalRender() {
        for (NGImageDisplayControllerLayerItem item : FLayers) {
            try {
                FCurrentLayer = item;
                super.InternalRender();
            }
            finally {
                FCurrentLayer = null;
            }
        }
    }

    @Override
    public void setImageName(String aImageName) {
        super.setImageName(aImageName);
        removeAllLayer();
        addLayer(aImageName);
    }

    public NGImageDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, "");
    }

    public NGImageDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName);
        FLayers = new ArrayList<NGImageDisplayControllerLayerItem>();
        addLayer(aImagename, 0);
        ImageScale = 1.0;
        ImageNumber = -1;
        MaxImageNumber = 1;
    }

    public double ImageScale;
    public Integer ImageNumber;
    public Integer MaxImageNumber;

    @Override
    public Boolean getInitialized() {
        return super.getInitialized() && FImage != null;
    }

    public void addLayer(String aImageName) {
        addLayer(aImageName, 0);
    }

    public void addLayer(String aImageName, Integer aZOrder) {
        NGImageDisplayControllerLayerItem item = new NGImageDisplayControllerLayerItem(aImageName, aZOrder);
        FLayers.add(item);
        Collections.sort(FLayers);
    }

    public void removeAllLayer() {
        FLayers.clear();
    }

}
