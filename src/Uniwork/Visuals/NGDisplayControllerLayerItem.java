package Uniwork.Visuals;

import Uniwork.Base.NGObject;

public class NGDisplayControllerLayerItem extends NGObject implements Comparable {

    protected NGDisplayController FDisplayController;
    protected String FImageName;
    protected Integer FZOrder;
    protected String FName;

    public NGDisplayControllerLayerItem(NGDisplayController aDisplayController, String aName, String aImageName, Integer aZOrder) {
        super();
        FDisplayController = aDisplayController;
        FName = aName;
        FImageName = aImageName;
        FZOrder = aZOrder;
        if (FZOrder < 0) {
            FZOrder = 0;
        }
        ImageScale = 1.0;
        ImageNumber = -1;
        MaxImageNumber = 1;
    }

    public NGDisplayController getDisplayController() {
        return FDisplayController;
    }

    public String getName() {
        return FName;
    }

    public String getImageName() {
        return FDisplayController.resolveImageName(this, FImageName);
    }

    public void setImageName(String aImageName) {
        FImageName = aImageName;
    }

    public Integer getZOrder() {
        return FZOrder;
    }

    @Override
    public int compareTo(Object o) {
        NGDisplayControllerLayerItem item = (NGDisplayControllerLayerItem)o;
        if (item.getZOrder() < getZOrder())
            return 1;
        else if (item.getZOrder() > getZOrder())
            return -1;
        else
            return 0;
    }

    public double ImageScale;
    public Integer ImageNumber;
    public Integer MaxImageNumber;

}
