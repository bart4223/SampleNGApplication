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
    protected Boolean FDoReceiveCall = false;
    protected NGPropertyItem FResultItem = null;
    protected NGObjectStack FCallStack;
    protected Boolean FPushCallStack = false;
    protected Boolean FDoGoto = false;

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

    protected void scanParseNode(NGObjectNode aObjectNode) {
        NGObjectNode token = aObjectNode;
        if (token instanceof NGScriptTokenRemark) {
            Nop();
        } else if (token instanceof NGScriptTokenGoto) {
            FCaller = null;
            FDoGoto = true;
            Iterator<NGObjectNode> itr = aObjectNode.getChilds();
            while (itr.hasNext()) {
                scanParseNode(itr.next());
            }
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
            Iterator<NGObjectNode> itr = aObjectNode.getChilds();
            while (itr.hasNext()) {
                scanParseNode(itr.next());
            }
        } else if (token instanceof NGScriptTokenParameter) {
            if (FDoGoto) {
                String s = ((NGScriptTokenParameter)token).getToken();
                FCallStack.push(Integer.parseInt(s));
            } else if (!FDoReceiveCall) {
                String str = ((NGScriptTokenParameter)token).getToken();
                Object value = str;
                if (str.startsWith(":")) {
                    value = FDataStore.get(str.substring(1, str.length()).toUpperCase());
                }
                FCaller.addParam(value);
            } else {
                FResultItem = FDataStore.set(((NGScriptTokenParameter)token).getToken().toUpperCase(), null);
            }
        } else if (token instanceof NGScriptTokenAllocation || token instanceof NGScriptTokenElseDecision) {
            FDoReceiveCall = true;
        } else if (token instanceof NGScriptTokenDecision) {
            FDoReceiveCall = true;
            FPushCallStack = true;
        }
    }

    protected void DoBeforeExecute(NGObjectNode aObjectNode) {
        scanParseNode(aObjectNode);
        if (FCaller != null) {
            raiseBeforeExecute(FCaller);
        }
    }

    protected void _BeforeExecute(NGObjectNode aObjectNode) {
        FCaller = null;
        FDoReceiveCall = false;
        FPushCallStack = false;
        FDoGoto = false;
        FResultItem = null;
        FCommandsCalled = 0;
        DoBeforeExecute(aObjectNode);
    }

    protected void DoExecute() {
        if (FCaller != null) {
            FCommandsCalled = FCommandsCalled + 1;
            if (FCaller.HasInvoker()) {
                FCaller.Invoke();
            } else {
                writeError("No caller available.");
            }
        }
    }

    protected void _Execute() {
        Integer CalledTokens;
        String[] script = FScript.split("\\n");
        for (int i = 0; i < script.length; i++) {
            CalledTokens = 0;
            FCallStack.Clear();
            FParser.Parse(script[i]);
            Iterator<NGObjectNode> itr = FParser.getParseTree().getRoot().getChilds();
            while (itr.hasNext()) {
                NGObjectNode token = itr.next();
                if (CalledTokens < 2) {
                    Boolean lOK = FCallStack.isEmpty();
                    if (!lOK) {
                        Object obj = FCallStack.pop();
                        if (obj instanceof Boolean) {
                            lOK = (Boolean)obj;
                        }
                    }
                    if (lOK) {
                        _BeforeExecute(token);
                        try {
                            DoExecute();
                            if (FCaller != null) {
                                CalledTokens++;
                            }
                        } finally {
                            _AfterExecute();
                            if (FDoGoto && !FCallStack.isEmpty()) {
                                Object obj = FCallStack.pop();
                                i = (Integer)obj;
                                i = i - 2;
                            }
                        }
                    }
                }
                if (FDoGoto) {
                    break;
                }
            }
        }
    }

    protected void _AfterExecute() {
        DoAfterExecute();
        if (FCaller != null) {
            if (FPushCallStack) {
                FCallStack.push(FCaller.getFirstResult());
            } else if (FResultItem != null) {
                FDataStore.set(FResultItem.getName(), FCaller.getFirstResult());
            }
        }
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
        FCallStack = new NGObjectStack();
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

    public String getVariablesAsString() {
        return FDataStore.toString();
    }

    public Iterator<NGPropertyItem> getVariables() {
        return FDataStore.getItemsAs();
    }

    public String getVariableAsString(String aVariable) {
        Object res = FDataStore.get(aVariable.toUpperCase());
        if (res == null) {
            res = "null";
        }
        return res.toString();
    }

    public void ClearVariables() {
        FDataStore.clear();
    }

}
