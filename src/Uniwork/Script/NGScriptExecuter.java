package Uniwork.Script;

import Uniwork.Base.*;
import Uniwork.Misc.NGStrings;

import java.util.ArrayList;
import java.util.Iterator;

public class NGScriptExecuter extends NGComponentManager {

    protected String FScript = "";
    protected NGScriptParser FParser;
    protected NGPropertyList FDataStore;
    protected NGObjectRequestCaller FCaller = null;
    protected NGObjectRequestInvoker FInvoker = null;
    protected Integer FCommandsCalled = 0;
    protected ArrayList<NGScriptExecuterListener> FEventListeners;

    protected void Nop() {
        // Nix machen ;o)
    }

    protected synchronized void raiseBeforeExecute(NGObjectRequestCaller aCaller) {
        NGScriptExecuterEvent event = new NGScriptExecuterEvent(this, aCaller);
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            NGScriptExecuterListener listener = (NGScriptExecuterListener)lItr.next();
            listener.handleBeforeExecute(event);
        }
    }

    protected synchronized void raiseAfterExecute(NGObjectRequestCaller aCaller) {
        NGScriptExecuterEvent event = new NGScriptExecuterEvent(this, aCaller);
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            NGScriptExecuterListener listener = (NGScriptExecuterListener)lItr.next();
            listener.handleAfterExecute(event);
        }
    }

    protected void scanParseTree(NGObjectNode aObjectNode) {
        Iterator<NGObjectNode> itr = aObjectNode.getChilds();
        while (itr.hasNext()) {
            NGObjectNode token = itr.next();
            if (token instanceof NGScriptTokenRemark) {
                Nop();
            } else if (token instanceof NGScriptTokenCommand) {
                FCaller = new NGObjectRequestCaller(FInvoker);
                String cmd = ((NGScriptTokenCommand)token).getToken();
                String objectname = "Application";
                String methodname;
                if (NGStrings.getStringCount(cmd, "\\.") == 1) {
                    methodname = NGStrings.getStringPos(cmd, "\\.", 1);
                } else {
                    objectname = NGStrings.getStringPos(cmd, "\\.", 1);
                    methodname = NGStrings.getStringPos(cmd, "\\.", 2);
                }
                FCaller.setObjectName(objectname);
                FCaller.setObjectMethod(methodname);
                scanParseTree(token);
            } else if (token instanceof NGScriptTokenParameter) {
                FCaller.addParam(((NGScriptTokenParameter)token).getToken());
            }
        }
    }

    protected void DoBeforeExecute() {
        scanParseTree(FParser.getParseTree().getRoot());
        if (FCaller != null) {
            raiseBeforeExecute(FCaller);
        }
    }

    protected void _BeforeExecute() {
        FCaller = null;
        FCommandsCalled = 0;
        FDataStore.clear();
        DoBeforeExecute();
    }

    protected void DoExecute() {
        if (FCaller != null) {
            FCommandsCalled = FCommandsCalled + 1;
            if (FCaller.HasInvoker()) {
                FCaller.Invoke();
            }
        }
    }

    protected void _Execute() {
        String[] script = FScript.split("\\n");
        for (int i = 0; i < script.length; i++) {
            FParser.Parse(script[i]);
            _BeforeExecute();
            try {
                DoExecute();
            } finally {
                _AfterExecute();
            }

        }
    }

    protected void _AfterExecute() {
        DoAfterExecute();
    }

    protected void DoAfterExecute() {
        if (FCaller != null) {
            raiseAfterExecute(FCaller);
        }
    }

    public NGScriptExecuter() {
        super();
        FParser = new NGScriptParser();
        registerComponent(FParser);
        FEventListeners = new ArrayList<NGScriptExecuterListener>();
        FDataStore = new NGPropertyList();
    }

    public void Execute(String aScript) {
        FScript = aScript;
        _Execute();
    }

    public void setInvoker(NGObjectRequestInvoker aInvoker) {
        FInvoker = aInvoker;
    }

    public NGObjectRequestInvoker getInvoker() {
        return FInvoker;
    }

    public Integer getCommandsCalled() {
        return FCommandsCalled;
    }

    public synchronized void addEventListener(NGScriptExecuterListener aListener)  {
        FEventListeners.add(aListener);
    }

    public synchronized void removeEventListener(NGScriptExecuterListener aListener)   {
        FEventListeners.remove(aListener);
    }

    public String getDataStoreValuesAsString() {
        return FDataStore.toString();
    }

}
