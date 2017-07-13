package Uniwork.Appl;

import Uniwork.Base.NGObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGApplicationDefinition extends NGObject {

    protected CopyOnWriteArrayList<NGApplicationModuleItemDefinition> Modules;

    public NGApplicationDefinition() {
        super();
    }

    public void setModules(CopyOnWriteArrayList<NGApplicationModuleItemDefinition> value) { Modules = value;}
    public CopyOnWriteArrayList<NGApplicationModuleItemDefinition> getModules() { return Modules; }

}
