package Uniwork.Appl;

import java.util.ArrayList;

public class NGConsoleDefinition extends NGApplicationModuleDefinition {

    protected ArrayList<NGConsoleDefinitionCommandItem> Commands;

    public NGConsoleDefinition() {
        super();
    }

    public void setCommands(ArrayList<NGConsoleDefinitionCommandItem> value) { Commands = value;}
    public ArrayList<NGConsoleDefinitionCommandItem> getCommands() { return Commands; }
    
}
