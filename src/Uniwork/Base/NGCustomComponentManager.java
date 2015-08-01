package Uniwork.Base;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class NGCustomComponentManager extends NGComponent {

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        for (NGComponent component : FComponents) {
            component.Initialize();
        }
    }

    @Override
    protected void DoFinalize() {
        super.DoFinalize();
        for (NGComponent component : FComponents) {
            component.Finalize();
        }
    }

    protected ArrayList<NGComponent> FComponents;

    protected Iterator<NGComponent> getComponents() {
        return FComponents.iterator();
    }

    protected void registerComponent(NGComponent aComponent) {
        FComponents.add(aComponent);
        writeInfo(String.format("%s [%s] registered.", aComponent.getClass().getName(), aComponent.getName()));
    }

    protected void unregisterComponent(NGComponent aComponent) {
        FComponents.remove(aComponent);
        writeInfo(String.format("%s [%s] unregistered.", aComponent.getClass().getName(), aComponent.getName()));
    }

    protected void unregisterComponent(String aName) {
        for (NGComponent comp : FComponents) {
            if (comp.getName().equals(aName)) {
                unregisterComponent(comp);
            }
        }
    }

    public NGCustomComponentManager() {
        this(null);
    }

    public NGCustomComponentManager(NGComponent aOwner) {
        this(aOwner, "");
    }

    public NGCustomComponentManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FComponents = new ArrayList<NGComponent>();
    }

    public NGComponent getComponent(String aName) {
        for (NGComponent comp : FComponents) {
            if (comp.getName().equals(aName)) {
                return comp;
            }
        }
        return null;
    }

}
