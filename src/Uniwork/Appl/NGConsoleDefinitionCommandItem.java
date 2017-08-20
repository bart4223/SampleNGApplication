package Uniwork.Appl;

import Uniwork.Base.NGObject;

public class NGConsoleDefinitionCommandItem extends NGObject {

    protected String FCommand;

    public NGConsoleDefinitionCommandItem() {
        super();
    }

    public void setCommand(String aCommand) {
        FCommand = aCommand;
    }

    public String getCommand() {
        return FCommand;
    }

}
