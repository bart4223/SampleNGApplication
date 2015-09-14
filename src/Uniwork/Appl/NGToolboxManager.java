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

    public NGCustomStageItem ShowToolbox(String aItemName) {
        return ShowToolbox(aItemName, null);
    }

    public NGCustomStageItem ShowToolbox(String aItemName, Object aContext) {
        return ShowToolbox(aItemName, "", aContext);
    }

    public NGCustomStageItem ShowToolbox(String aItemName, String aCaption) {
        return ShowToolbox(aItemName, aCaption, null);
    }

    public NGCustomStageItem ShowToolbox(String aItemName, String aCaption, Object aContext) {
        return ShowToolbox(aItemName, aItemName, aCaption, aContext);
    }

    public NGCustomStageItem ShowToolbox(String aItemName, String aName, String aCaption, Object aContext) {
        NGCustomStageItem res = CreateToolbox(aItemName, aName, aCaption, aContext);
        res.Show();
        return res;
    }

    public NGCustomStageItem CreateToolbox(String aItemName) {
        return CreateToolbox(aItemName, "");
    }

    public NGCustomStageItem CreateToolbox(String aItemName, String aCaption) {
        return CreateToolbox(aItemName, aCaption, null);
    }

    public NGCustomStageItem CreateToolbox(String aItemName, String aCaption, Object aContext) {
        return CreateToolbox(aItemName, aItemName, aCaption, aContext);
    }

    public NGCustomStageItem CreateToolbox(String aItemName, String aName, String aCaption, Object aContext) {
        NGCustomStageItem res = null;
        for (NGCustomStageItem item : FItems) {
            if (item.getName().equals(getFullname(aName)) && (!item.IsStageShowing() || item.getUnique())) {
                res = item;
                break;
            }
        }
        if (res == null) {
            res = addStageItem(aItemName, aName);
            res.Initialize();
        }
        if (aCaption.length() > 0)
            res.setCaption(aCaption);
        res.setContext(aContext);
        return res;
    }

}
