package Uniwork.Visuals;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObjectRequestItem;
import Uniwork.Misc.NGStrings;

public class NGStageController extends NGCustomStageController {

    protected void Invoke(NGObjectRequestItem aRequest) {
        NGApplication.Application.Invoke(aRequest);
    }

    protected void Invoke(String aObject, String aMethod) {
        String object = aObject;
        if (FStageItem != null && !aObject.equals("Application")) {
            String name = NGStrings.getFirstString(FStageItem.getName(), ".");
            object = NGStrings.addString(name, aObject, ".");
        }
        NGApplication.Application.Invoke(object, aMethod);
    }

    public NGStageController() {
        this(null);
    }

    public NGStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
