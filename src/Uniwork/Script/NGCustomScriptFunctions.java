package Uniwork.Script;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestRegistration;

public abstract class NGCustomScriptFunctions extends NGComponent {

    protected NGObjectRequestRegistration FORR;
    protected Object FDelegate;

    protected NGObjectRequestMethod registerObjectRequest(String aName, String aMethod, String aObjectMethod) {
        return FORR.registerObjectRequest(aName, getInvokeObject(), aMethod, aObjectMethod);
    }

    public NGCustomScriptFunctions(NGObjectRequestRegistration aORR) {
        this(aORR, null);
    }

    public NGCustomScriptFunctions(NGObjectRequestRegistration aORR, Object aDelegate) {
        super();
        FORR = aORR;
        FDelegate = aDelegate;
    }

    public Object getInvokeObject() {
        Object res = FDelegate;
        if (res == null) {
            res = this;
        }
        return res;
    }

}
