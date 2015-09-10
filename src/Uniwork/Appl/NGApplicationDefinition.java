package Uniwork.Appl;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public class NGApplicationDefinition extends NGObject {

    protected ArrayList<NGApplicationModuleItemDefinition> Modules;

    public NGApplicationDefinition() {
        super();
    }

    public void setModules(ArrayList<NGApplicationModuleItemDefinition> value) { Modules = value;}
    public ArrayList<NGApplicationModuleItemDefinition> getModules() { return Modules; }

}
