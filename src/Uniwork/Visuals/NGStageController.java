package Uniwork.Visuals;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObjectRequestItem;

public class NGStageController extends NGCustomStageController {

    protected void Invoke(NGObjectRequestItem aRequest) {
        NGApplication.Application.Invoke(aRequest);
    }

    protected void Invoke(String aObject, String aMethod) {
        NGApplication.Application.Invoke(aObject, aMethod);
    }

    public NGStageController() {
        this(null);
    }

    public NGStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
