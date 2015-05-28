package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGCustomComponentManager;

import java.util.ArrayList;
import java.util.Iterator;

public class NGApplicationModuleManager extends NGCustomComponentManager {

    NGApplicationModuleManager() {
        this(null);
    }

    NGApplicationModuleManager(NGComponent aOwner) {
        this(aOwner, "");
    }

    NGApplicationModuleManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
    }

    public NGCustomApplicationModule getModule(String aName) {
        return (NGCustomApplicationModule)getComponent(aName);
    }

    public void registerModule(NGCustomApplicationModule aModule) {
        registerComponent(aModule);
    }

    public void unregisterModule(NGCustomApplicationModule aModule) {
        unregisterComponent(aModule);
    }

    public Iterator<NGCustomApplicationModule> getModules() {
        ArrayList<NGCustomApplicationModule> modules = new ArrayList<NGCustomApplicationModule>();
        for (NGComponent comp : FComponents) {
            modules.add((NGCustomApplicationModule)comp);
        }
        return modules.iterator();
    }

}
