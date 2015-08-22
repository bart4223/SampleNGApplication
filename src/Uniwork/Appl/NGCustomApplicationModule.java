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


    public NGCustomApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        Application = NGApplication.Application;
        FComponentManager = new NGComponentManager(this);
        FDescription = "";
    }

    public String getDescription() {
        return FDescription;
    }

    public void setDescription(String aDescription) {
        FDescription = aDescription;
    }

    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        FComponentManager.setLogManager(aLogManager);
    }

    public NGApplication Application;

}
