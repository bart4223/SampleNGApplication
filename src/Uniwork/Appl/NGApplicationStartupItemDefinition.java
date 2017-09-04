package Uniwork.Appl;

import Uniwork.Base.NGObject;

public class NGApplicationStartupItemDefinition extends NGObject {

    protected String Name = "";

    public NGApplicationStartupItemDefinition() {
        super();
    }

    public void setName(String value) {
        Name = value;
    }
    public String getName() {
        return Name;
    }

}
