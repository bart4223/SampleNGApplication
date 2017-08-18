package Uniwork.Misc;

import Uniwork.Base.*;
import Uniwork.Script.NGScriptExecuter;

public class NGConsoleManager extends NGComponentManager {

    protected NGObjectStack FCommands;
    protected NGScriptExecuter FExecuter;
    protected NGObjectRequestInvoker FInvoker;

    public NGConsoleManager(NGObjectRequestInvoker aInvoker) {
        super();
        FExecuter = new NGScriptExecuter();
        registerComponent(FExecuter);
        FExecuter.setInvoker(aInvoker);
        FCommands = new NGObjectStack();
        FCommands.push("help");
        FCommands.push("console.runscript resources/scripts/sample10.as");
        FInvoker = aInvoker;
    }

    public Integer ExecuteCommand(String aCommand) {
        FCommands.push(aCommand);
        RunScript(aCommand);
        return FCommands.getSize() - 1;
    }

    public void RunScript(String aScript) {
        FExecuter.Execute(aScript);
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
