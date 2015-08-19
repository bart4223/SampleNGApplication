package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGCustomComponentManager;
import Uniwork.Misc.NGStrings;

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

    public NGCustomApplicationModule addModule(Class<?> aModuleClass) {
        return this.addModule(aModuleClass, String.format("%s%d", NGStrings.getLastString(aModuleClass.getName(), "."), FComponents.size()), "");
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, String aName) {
        return this.addModule(aModuleClass, aName, "");
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, String aName, String aDescription) {
        try {
            NGCustomApplicationModule res = (NGCustomApplicationModule)aModuleClass.getConstructor(NGComponent.class, String.class, String.class).newInstance(this, aName, aDescription);
            res.setLogManager(FLogManager);
            registerModule(res);
            return res;
        }
        catch (Exception e) {
            writeError(e.getMessage());
        }
        return null;
    }

}
