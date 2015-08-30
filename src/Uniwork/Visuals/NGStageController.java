package Uniwork.Visuals;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObjectRequestItem;
import Uniwork.Misc.NGStrings;

public class NGStageController extends NGCustomStageController {

    protected class ComboboxText {

        protected String FID;
        protected String FName;

        public ComboboxText(String aID, String aName) {
            FID = aID;
            FName = aName;
        }

        public String getId() {
            return FID;
        }

        public String getName() {
            return FName;
        }

        @Override
        public String toString() {
            return FName;
        }

    }

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

    protected Boolean getConfigurationPropertyAsBoolean(String aName, Boolean aDefault) {
        return NGApplication.Application.getConfigurationPropertyAsBoolean(aName, aDefault);
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
