package Uniwork.Base;

import Uniwork.Misc.NGStrings;

import java.util.Iterator;

public class NGScriptExecuter extends NGComponentManager {

    protected String FScript = "";
    protected NGScriptParser FParser;
    protected NGPropertyList FDataStorage;
    protected NGObjectRequestCaller FCaller = null;
    protected NGObjectRequestInvoker FInvoker = null;
    protected Integer FCommandsCalled = 0;

    protected void Nop() {
        // Nix machen ;o)
    }

    protected void scanParseTree(NGObjectNode aObjectNode) {
        Iterator<NGObjectNode> itr = aObjectNode.getChilds();
        while (itr.hasNext()) {
            NGObjectNode token = itr.next();
            if (token instanceof NGParseTokenRemark) {
                Nop();
            } else if (token instanceof NGParseTokenCommand) {
                FCaller = new NGObjectRequestCaller(FInvoker);
                String cmd = ((NGParseTokenCommand)token).getToken();
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
            } else if (token instanceof NGParseTokenParameter) {
                FCaller.addParam(((NGParseTokenParameter)token).getToken());
            }
        }
    }

    protected void DoBeforeExecute() {
        scanParseTree(FParser.getParseTree().getRoot());
    }

    protected void _BeforeExecute() {
        FCaller = null;
        FCommandsCalled = 0;
        FDataStorage.clear();
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

    }

    public NGScriptExecuter() {
        super();
        FParser = new NGScriptParser();
        registerComponent(FParser);
        FDataStorage = new NGPropertyList();
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
    
}
