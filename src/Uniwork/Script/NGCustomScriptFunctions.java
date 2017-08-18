package Uniwork.Script;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGObjectRequestRegistration;

public abstract class NGCustomScriptFunctions extends NGComponent {

    protected NGObjectRequestRegistration FORR;

    protected void registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        if (FORR != null) {
            FORR.registerObjectRequest(aName, aObject, aMethod, aObjectMethod);
        }
    }

    public NGCustomScriptFunctions(NGObjectRequestRegistration aORR) {
        super();
        FORR = aORR;
        writeInfo("Cool");
    }

}
