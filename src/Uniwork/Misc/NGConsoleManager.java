package Uniwork.Misc;

import Uniwork.Base.*;
import Uniwork.Script.NGScriptExecuter;
import Uniwork.Script.NGScriptExecuterEvent;
import Uniwork.Script.NGScriptExecuterListener;

public class NGConsoleManager extends NGComponentManager implements NGScriptExecuterListener {

    protected NGObjectStack FCommands;
    protected NGScriptExecuter FExecuter;
    protected NGObjectRequestInvoker FInvoker;

    public NGConsoleManager(NGObjectRequestInvoker aInvoker) {
        super();
        FExecuter = new NGScriptExecuter();
        registerComponent(FExecuter);
        FExecuter.setInvoker(aInvoker);
        FExecuter.addEventListener(this);
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

    @Override
    public void handleBeforeExecute(NGScriptExecuterEvent e) {
        writeInfo(String.format("Execute -> %s", e.getCaller().toString()));
    }

    @Override
    public void handleAfterExecute(NGScriptExecuterEvent e) {

    }
}
