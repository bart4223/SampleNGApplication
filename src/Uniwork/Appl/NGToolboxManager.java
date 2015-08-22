package Uniwork.Appl;

import Uniwork.Base.NGComponent;

public class NGToolboxManager extends NGStageManager {

    public NGToolboxManager() {
        this("");
    }

    public NGToolboxManager(String aName) {
        this(null, aName);
    }

    public NGToolboxManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
    }

    public void ShowToolbox(String aItemName, Object aContext) {
        ShowToolbox(aItemName, aItemName, aContext);
    }

    public void ShowToolbox(String aItemName, String aName, Object aContext) {
        NGCustomStageItem item = getItem(getFullname(aName));
        if (item == null) {
            item = addStageItem(aItemName, aName);
            item.setContext(aContext);
            item.Initialize();
        }
        else {
            item.setContext(aContext);
            item.Show();
        }
    }

}
