package Uniwork.Misc;

import Uniwork.Base.*;
import Uniwork.Script.NGScriptExecuter;
import Uniwork.Script.NGScriptExecuterEvent;
import Uniwork.Script.NGScriptExecuterListener;

public class NGConsoleManager extends NGComponentManager implements NGScriptExecuterListener {

    protected NGObjectStack FCommands;
    protected NGScriptExecuter FExecuter;
    protected NGObjectRequestInvoker FInvoker;
    protected Boolean FEcho = true;

    public NGConsoleManager(NGObjectRequestInvoker aInvoker) {
        super();
        FExecuter = new NGScriptExecuter();
        registerComponent(FExecuter);
        FExecuter.setInvoker(aInvoker);
        FExecuter.addEventListener(this);
        FCommands = new NGObjectStack();
        pushCommand("help");
        FInvoker = aInvoker;
    }

    public Integer ExecuteCommand(String aCommand) {
        FCommands.push(aCommand);
        FExecuter.Execute(aCommand);
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

    @Override
    public void handleBeforeExecute(NGScriptExecuterEvent e) {
        if (FEcho) {
            writeInfo(String.format("Execute -> %s", e.getCaller().toString()));
        }
    }

    @Override
    public void handleAfterExecute(NGScriptExecuterEvent e) {

    }

    public void EchoOff() {
        FEcho = false;
    }

    public void EchoOn() {
        FEcho = true;
    }

    public void RunScript(String aScript) {
        try {
            String script = NGMisc.LoadFileContentUnsafe(aScript);
            writeInfo(String.format("Application script %s loaded.", aScript));
            FExecuter.Execute(script);
        } catch (Exception e) {
            writeError(e.getMessage());
        }
    }

    public void ShowScript(String aScript) {
        try {
            String script = NGMisc.LoadFileContentUnsafe(aScript);
            writeInfo(String.format("Application script %s loaded.", aScript));
            String[] lines = script.split("\\n");
            for (int i = 0; i < lines.length; i++) {
                writeInfo(lines[i]);
            }
        } catch (Exception e) {
            writeError(e.getMessage());
        }
    }

    public void ShowVariables() {
        String variables = FExecuter.getVariablesAsString();
        if (variables.length() == 0) {
            writeWarning("No variables existing.");
        } else {
            writeInfo(variables);
        }
    }

    public void ShowVariable(String aVariable) {
        writeInfo(String.format("%s=%s", aVariable, FExecuter.getVariableAsString(aVariable)));
    }

    public void ClearVariables() {
        FExecuter.ClearVariables();
        writeInfo("Variables cleared.");
    }

    public void pushCommand(String aCommand) {
        FCommands.push(aCommand);
    }

}
