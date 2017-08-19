package Uniwork.Script;

import Uniwork.Base.NGObjectRequestCaller;

import java.util.EventObject;

public class NGScriptExecuterEvent extends EventObject {

    protected NGObjectRequestCaller FCaller;

    public NGScriptExecuterEvent(Object source, NGObjectRequestCaller aCaller) {
        super(source);
        FCaller = aCaller;
    }

    public NGObjectRequestCaller getCaller() {
        return FCaller;
    }

}
