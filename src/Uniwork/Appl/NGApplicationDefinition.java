package Uniwork.Appl;

import Uniwork.Base.NGObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGApplicationDefinition extends NGObject {

    protected CopyOnWriteArrayList<NGApplicationModuleItemDefinition> Modules;
    protected CopyOnWriteArrayList<NGApplicationScriptItemDefinition> Scripts;
    protected CopyOnWriteArrayList<NGApplicationStartupItemDefinition> Startups;
    protected CopyOnWriteArrayList<NGApplicationShutdownItemDefinition> Shutdowns;

    public NGApplicationDefinition() {
        super();
    }

    public void setModules(CopyOnWriteArrayList<NGApplicationModuleItemDefinition> value) { Modules = value;}
    public CopyOnWriteArrayList<NGApplicationModuleItemDefinition> getModules() { return Modules; }

    public void setScripts(CopyOnWriteArrayList<NGApplicationScriptItemDefinition> value) { Scripts = value;}
    public CopyOnWriteArrayList<NGApplicationScriptItemDefinition> getScripts() { return Scripts; }

    public void setStartups(CopyOnWriteArrayList<NGApplicationStartupItemDefinition> value) { Startups = value;}
    public CopyOnWriteArrayList<NGApplicationStartupItemDefinition> getStartups() { return Startups; }

    public void setShutdowns(CopyOnWriteArrayList<NGApplicationShutdownItemDefinition> value) { Shutdowns = value;}
    public CopyOnWriteArrayList<NGApplicationShutdownItemDefinition> getShutdowns() { return Shutdowns; }

}
