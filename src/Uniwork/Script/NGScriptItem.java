package Uniwork.Script;

import Uniwork.Base.NGObject;

public class NGScriptItem extends NGObject {

    protected String FName;
    protected String FScript;
    protected String FDescription;
    protected String FFileName;

    public NGScriptItem(String aName, String aFilename, String aDescription) {
        super();
        FName = aName;
        FFileName = aFilename;
        FDescription = aDescription;
    }

    public String getName() {
        return FName;
    }

    public String getScript() {
        return FScript;
    }

    public void setScript(String aScript) {
        FScript = aScript;
    }

    public String getDescription() {
        return FDescription;
    }

    public String getFileName() {
        return FFileName;
    }

}
