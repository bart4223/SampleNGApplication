package Uniwork.Appl;

import Uniwork.Base.NGObject;

public class NGStageItemClass extends NGObject {

    protected String FName;
    protected Class<NGCustomStageItem> FItemClass;

    public NGStageItemClass(String aName, Class<NGCustomStageItem> aItemClass) {
        super();
        FName = aName;
        FItemClass = aItemClass;
    }

    public String getName() {
        return FName;
    }

    public Class<NGCustomStageItem> getItemClass() {
        return FItemClass;
    }

}
