package Uniwork.Visuals;

import Uniwork.Misc.NGStrings;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;

public class NGImageDisplayController extends NGDisplayController {

    public static String CLAYERBACKGROUND = "Background";

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
        if (FCurrentLayer.equals(FLayers.get(0))) {
            double x = getPositionX() - getViewPositionX() + 1;
            double y = getPositionY() - getViewPositionY() + 1;
            FGC.clearRect(x, y, FWidth - 1, FHeight - 1);
        }
        FImageName = FCurrentLayer.getImageName();
        obtainImage();
    }

    @Override
    protected void RecalculateDimensions() {
        FWidth = 0;
        FHeight = 0;
        for (NGImageDisplayControllerLayerItem layer : FLayers) {
            Image img = getImage(layer.getImageName());
            if (img != null) {
                double width = img.getWidth() * layer.ImageScale;
                if (width > FWidth) {
                    FWidth = width;
                }
                double height = img.getHeight() * layer.ImageScale;
                if (height > FHeight) {
                    FHeight = height;
                }
            }
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
        for (NGImageDisplayControllerLayerItem layer : FLayers) {
            try {
                FCurrentLayer = layer;
                super.InternalRender();
            }
            finally {
                FCurrentLayer = null;
            }
        }
    }

    protected NGImageDisplayControllerLayerItem getLayer(String aName) {
        for (NGImageDisplayControllerLayerItem layer : FLayers) {
            if (layer.getName().equals(aName)) {
                return layer;
            }
        }
        return null;
    }

    @Override
    public void setImageName(String aImageName) {
        super.setImageName(aImageName);
        removeLayer(CLAYERBACKGROUND);
        addLayer(CLAYERBACKGROUND, aImageName);
    }

    public NGImageDisplayController(Canvas aCanvas, String aName) {
        this(aCanvas, aName, "");
    }

    public NGImageDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName);
        FLayers = new ArrayList<NGImageDisplayControllerLayerItem>();
        addLayer(CLAYERBACKGROUND, aImagename);
    }

    @Override
    public Boolean getInitialized() {
        return super.getInitialized() && FImage != null;
    }

    @Override
    public void setProperty(Object aObject, String aName, Object aValue) {
        if (aName.contains(".")) {
            NGImageDisplayControllerLayerItem layer = getLayer(NGStrings.getStringPos(aName, "\\.", 1));
            if (layer != null) {
                layer.setProperty(layer, NGStrings.getStringPos(aName, "\\.", 2), aValue);
            }
            else
                super.setProperty(aObject, aName, aValue);
        }
        else
            super.setProperty(aObject, aName, aValue);
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        if (aName.contains(".")) {
            NGImageDisplayControllerLayerItem layer = getLayer(NGStrings.getStringPos(aName, "\\.", 1));
            if (layer != null) {
                return layer.getProperty(layer, NGStrings.getStringPos(aName, "\\.", 2));
            }
            else
                return super.getProperty(aObject, aName);
        }
        else
            return super.getProperty(aObject, aName);
    }

    public void addLayer(String aName, String aImageName) {
        addLayer(aName, aImageName, 0);
    }

    public void addLayer(String aName, String aImageName, Integer aZOrder) {
        NGImageDisplayControllerLayerItem item = new NGImageDisplayControllerLayerItem(aName, aImageName, aZOrder);
        FLayers.add(item);
        Collections.sort(FLayers);
    }

    public void removeLayer(String aName) {
        NGImageDisplayControllerLayerItem layer = getLayer(aName);
        FLayers.remove(layer);
    }

    public void removeAllLayer() {
        FLayers.clear();
    }

}
