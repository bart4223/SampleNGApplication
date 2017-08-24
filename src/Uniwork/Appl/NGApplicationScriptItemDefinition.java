package Uniwork.Appl;

import Uniwork.Base.NGObject;

public class NGApplicationScriptItemDefinition extends NGObject {

    protected String Name = "";
    protected String Filename = "";
    protected String Description = "";

    public NGApplicationScriptItemDefinition() {
        super();
    }

    public void setName(String value) {
        Name = value;
    }
    public String getName() {
        return Name;
    }

    public void setFilename(String value) {
        Filename = value;
    }
    public String getFilename() {
        return Filename;
    }

    public void setDescription(String value) {
        Description = value;
    }
    public String getDescription() {
        return Description;
    }

}
