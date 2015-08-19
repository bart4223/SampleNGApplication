package Uniwork.Appl;

import Uniwork.Base.*;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGStrings;

public class NGCustomApplicationModule extends NGComponent {

    protected String FDescription = "";
    protected NGComponentManager FComponentManager;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FComponentManager.Initialize();
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        registerObjectRequests();
    }

    protected void registerObjectRequests() {

    }

    protected NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        String name = NGStrings.addString(FName, aName, ".");
        return NGApplication.Application.registerObjectRequest(name, aObject, aMethod, aObjectMethod);
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
