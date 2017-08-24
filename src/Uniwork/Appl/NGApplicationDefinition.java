package Uniwork.Appl;

import Uniwork.Base.NGObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGApplicationDefinition extends NGObject {

    protected CopyOnWriteArrayList<NGApplicationModuleItemDefinition> Modules;
    protected CopyOnWriteArrayList<NGApplicationScriptItemDefinition> Scripts;

    public NGApplicationDefinition() {
        super();
    }

    public void setModules(CopyOnWriteArrayList<NGApplicationModuleItemDefinition> value) { Modules = value;}
    public CopyOnWriteArrayList<NGApplicationModuleItemDefinition> getModules() { return Modules; }

    public void setScripts(CopyOnWriteArrayList<NGApplicationScriptItemDefinition> value) { Scripts = value;}
    public CopyOnWriteArrayList<NGApplicationScriptItemDefinition> getScripts() { return Scripts; }

}
