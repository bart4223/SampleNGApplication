package Uniwork.Visuals;

import Uniwork.Base.NGObject;

public class NGImageDisplayControllerLayerItem extends NGObject implements Comparable{

    protected String FImageName;
    protected Integer FZOrder;

    public NGImageDisplayControllerLayerItem(String aImageName, Integer aZOrder) {
        super();
        FImageName = aImageName;
        FZOrder = aZOrder;
    }

    public String getImageName() {
        return FImageName;
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
}
