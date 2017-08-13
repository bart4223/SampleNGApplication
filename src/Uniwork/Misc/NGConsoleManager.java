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
        FCommands.push("help");
        FCommands.push("console.test");
        FCommands.push("application.addmodule A B");
        FInvoker = aInvoker;
    }

    public Integer ExecuteCommand(String aCommand) {
        FCommands.push(aCommand);
        NGObjectRequestCaller caller = new NGObjectRequestCaller(FInvoker);
        String objectname = "Application";
        String methodname;
        if (NGStrings.getStringCount(aCommand, "\\.") == 1) {
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
