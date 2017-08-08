package Uniwork.Misc;

import Uniwork.Base.NGObject;
import Uniwork.Base.NGObjectRequestCaller;
import Uniwork.Base.NGObjectRequestInvoker;
import Uniwork.Base.NGObjectStack;

public class NGConsoleManager extends NGObject {

    protected NGObjectStack FCommands;
    protected NGObjectRequestInvoker FInvoker;

    public NGConsoleManager(NGObjectRequestInvoker aInvoker) {
        super();
        FCommands = new NGObjectStack();
        FInvoker = aInvoker;
    }

    public Integer ExecuteCommand(String aCommand) {
        FCommands.push(aCommand);
        NGObjectRequestCaller caller = new NGObjectRequestCaller(FInvoker);
        Integer count = NGStrings.getStringCount(aCommand, "\\.");
        String objectname = "Application";
        String methodname;
        if (count == 1) {
            methodname = NGStrings.getStringPos(aCommand, "\\.", 1);
        } else {
            objectname = NGStrings.getStringPos(aCommand, "\\.", 1);
            methodname = NGStrings.getStringPos(aCommand, "\\.", 2);
        }
        caller.setObjectName(objectname);
        caller.setObjectMethod(methodname);
        caller.Invoke();
        return FCommands.getSize() - 1;
    }

    public NGObjectRequestInvoker getInvoker() {
        return FInvoker;
    }

    public String getCommand(Integer aIndex) {
        return (String)FCommands.get(aIndex);
    }

    public Integer getCommandCount() {
        return FCommands.getSize();
    }

}
