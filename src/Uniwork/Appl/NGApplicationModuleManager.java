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

    public Integer getModuleCount() {
        return FComponents.size();
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass) {
        return this.addModule(aModuleClass, String.format("%s%d", NGStrings.getLastString(aModuleClass.getName(), "."), FComponents.size()));
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, String aName) {
        try {
            NGCustomApplicationModule res = (NGCustomApplicationModule)aModuleClass.getConstructor(NGComponent.class, String.class).newInstance(this, aName);
            res.setLogManager(FLogManager);
            registerModule(res);
            return res;
        }
        catch (Exception e) {
            writeError(e.getMessage());
        }
        return null;
    }

    public NGCustomApplicationModule getModuleByClassname(String Classname) {
        Iterator<NGCustomApplicationModule> itr = getModules();
        while (itr.hasNext()) {
            NGCustomApplicationModule module = itr.next();
            if (NGStrings.getLastString(module.getClass().getName(), ".").equals(Classname))
                return module;
        }
        return null;
    }

}
