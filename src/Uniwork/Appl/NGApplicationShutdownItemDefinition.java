package Uniwork.Appl;

import Uniwork.Base.NGObject;

public class NGApplicationShutdownItemDefinition extends NGObject {

    protected String Name = "";

    public NGApplicationShutdownItemDefinition() {
        super();
    }

    public void setName(String value) {
        Name = value;
    }
    public String getName() {
        return Name;
    }

}
