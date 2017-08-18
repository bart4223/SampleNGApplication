package Uniwork.Script;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestRegistration;

public abstract class NGCustomScriptFunctions extends NGComponent {

    protected NGObjectRequestRegistration FORR;
    protected Object FDelegate;
    protected String FDomain;

    protected NGObjectRequestMethod registerObjectRequest(String aMethod, String aObjectMethod) {
        return FORR.registerObjectRequest(FDomain, getInvokeObject(), aMethod, aObjectMethod);
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
