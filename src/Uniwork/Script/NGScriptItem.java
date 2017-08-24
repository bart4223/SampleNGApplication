package Uniwork.Script;

import Uniwork.Base.NGObject;

public class NGScriptItem extends NGObject {

    protected String FName;
    protected String FScript;
    protected String FDescription;

    public NGScriptItem(String aName, String aScript, String aDescription) {
        super();
        FName = aName;
        FScript = aScript;
        FDescription = aDescription;
    }

    public String getName() {
        return FName;
    }

    public String getScript() {
        return FScript;
    }

    public String getDescription() {
        return FDescription;
    }

}
