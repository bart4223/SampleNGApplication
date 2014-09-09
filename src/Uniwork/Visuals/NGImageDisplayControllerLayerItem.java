package Uniwork.Visuals;

import Uniwork.Base.NGObject;

public class NGImageDisplayControllerLayerItem extends NGObject implements Comparable{

    protected String FImageName;
    protected Integer FZOrder;
    protected String FName;

    public NGImageDisplayControllerLayerItem(String aName, String aImageName, Integer aZOrder) {
        super();
        FName = aName;
        FImageName = aImageName;
        FZOrder = aZOrder;
    }

    public String getName() {
        return FName;
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
