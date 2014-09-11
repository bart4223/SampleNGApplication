package Uniwork.Visuals;

import Uniwork.Base.NGObject;

public class NGImageDisplayControllerLayerItem extends NGObject implements Comparable {

    protected String FImageName;
    protected Integer FZOrder;
    protected String FName;

    public NGImageDisplayControllerLayerItem(String aName, String aImageName, Integer aZOrder) {
        super();
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

    public String getName() {
        return FName;
    }

    public String getImageName() {
        String result = FImageName;
        if (MaxImageNumber > 1) {
            result = String.format(FImageName, ImageNumber%MaxImageNumber);
        }
        else if (MaxImageNumber == 1 && ImageNumber >= 0) {
            result = String.format(FImageName, ImageNumber);
        }
        return result;
    }

    public Integer getZOrder() {
        return FZOrder;
    }

    @Override
    public int compareTo(Object o) {
        NGImageDisplayControllerLayerItem item = (NGImageDisplayControllerLayerItem)o;
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
