package Uniwork.Script;

import Uniwork.Base.NGComponentManager;
import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsManager extends NGComponentManager {

    protected NGObjectRequestRegistration FORR;

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        registerComponent(new NGScriptFunctionsBase(FORR));
    }

    public NGScriptFunctionsManager(NGObjectRequestRegistration aORR) {
        super();
        FORR = aORR;
    }

}
