package Uniwork.Visuals;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObjectRequestItem;
import Uniwork.Misc.NGStrings;

public class NGStageController extends NGCustomStageController {

    protected String getObjectRequestName(String aObject) {
        String res = aObject;
        if (FStageItem != null && !aObject.equals("Application")) {
            String name = NGStrings.getFirstString(FStageItem.getName(), ".");
            res = NGStrings.addString(name, aObject, ".");
        }
        return res;
    }

    protected void Invoke(NGObjectRequestItem aRequest) {
        NGApplication.Application.Invoke(aRequest);
    }

    protected void Invoke(String aObject, String aMethod) {
        NGApplication.Application.Invoke(getObjectRequestName(aObject), aMethod);
    }

    protected NGObjectRequestItem newObjectRequest(String aObject, String aMethod) {
        NGObjectRequestItem res = new NGObjectRequestItem(getObjectRequestName(aObject), aMethod);
        return res;
    }

    public NGStageController() {
        this(null);
    }

    public NGStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
