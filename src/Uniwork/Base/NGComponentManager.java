package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

public class NGComponentManager extends NGCustomComponentManager {

    public NGComponentManager() {
        this(null);
    }

    public NGComponentManager(NGComponent aOwner) {
        this(aOwner, "");
    }

    public NGComponentManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
    }

    public void registerComponent(NGComponent aComponent) {
        super.registerComponent(aComponent);
    }

    public void unregisterComponent(NGComponent aComponent) {
        super.registerComponent(aComponent);
    }

    @Override
    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        for (NGComponent component : FComponents) {
            component.setLogManager(aLogManager);
        }
    }

}
