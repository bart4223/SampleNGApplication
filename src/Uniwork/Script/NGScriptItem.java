package Uniwork.Script;

import Uniwork.Base.NGObject;

public class NGScriptItem extends NGObject {

    protected String FName;
    protected String FScript;
    protected String FDescription;
    protected String FFileName;

    public NGScriptItem(String aName, String aScript, String aDescription) {
        this(aName, aScript, "", aDescription);
    }

    public NGScriptItem(String aName, String aScript, String aFilename, String aDescription) {
        super();
        FName = aName;
        FScript = aScript;
        FFileName = aFilename;
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

    public String getFileName() {
        return FFileName;
    }

}
