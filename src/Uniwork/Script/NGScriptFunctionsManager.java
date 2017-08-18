package Uniwork.Script;

import Uniwork.Base.NGComponentManager;
import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsManager extends NGComponentManager {

    protected NGObjectRequestRegistration FORR;

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGCustomScriptFunctions sf = new NGScriptFunctionsBase(FORR);
        sf.setLogManager(FLogManager);
        registerComponent(sf);
    }

    public NGScriptFunctionsManager(NGObjectRequestRegistration aORR) {
        super();
        FORR = aORR;
    }

}
