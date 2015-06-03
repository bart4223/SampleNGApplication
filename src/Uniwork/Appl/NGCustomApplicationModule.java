package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGComponentManager;
import Uniwork.Misc.NGLogManager;

public abstract class NGCustomApplicationModule extends NGComponent {

    protected String FDescription = "";
    protected NGComponentManager FComponentManager;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FComponentManager.Initialize();
    }

    protected void DoFinalize() {
        FComponentManager.Finalize();
        super.DoFinalize();
    }


    public NGCustomApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName);
        FComponentManager = new NGComponentManager(this);
        FDescription = aDescription;
    }

    public String getDescription() {
        return FDescription;
    }

    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        FComponentManager.setLogManager(aLogManager);
    }

}
