package Uniwork.Script;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestRegistration;

public abstract class NGCustomScriptFunctions extends NGComponent {

    protected NGObjectRequestRegistration FORR;

    protected NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        return FORR.registerObjectRequest(aName, aObject, aMethod, aObjectMethod);
    }

    public NGCustomScriptFunctions(NGObjectRequestRegistration aORR) {
        super();
        FORR = aORR;
        writeInfo("Cool");
    }

}
