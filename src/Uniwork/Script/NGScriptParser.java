package Uniwork.Script;

import Uniwork.Base.NGTextParser;

public class NGScriptParser extends NGTextParser {

    public String CRemark = "//";
    public String CAllocation = "=>";
    public String CDecision = "??";
    public String CElseDecision = "!!";

    protected NGScriptTokenCommand FCommand = null;

    @Override
    protected void DoBeforeParse() {
        super.DoBeforeParse();
        FCommand = null;
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        addSpecialStart(CRemark);
    }

    @Override
    protected void DoSpecialStartsTokenFound(String aSpecialStart, String aSpecialToken) {
        super.DoSpecialStartsTokenFound(aSpecialStart, aSpecialToken);
        if (aSpecialStart == CRemark) {
            new NGScriptTokenRemark(FParseTree.getRoot(), aSpecialToken);
        }
    }

    @Override
    protected void DoTokenFound(String aToken) {
        if (FCommand == null) {
            FCommand = new NGScriptTokenCommand(FParseTree.getRoot(), aToken);
        } else if (aToken.equals(CAllocation)){
            new NGScriptTokenAllocation(FCommand, aToken);
        } else if (aToken.equals(CDecision)) {
            new NGScriptTokenDecision(FCommand, aToken);
            FCommand = null;
        } else if (aToken.equals(CElseDecision)) {
            new NGScriptTokenElseDecision(FCommand, aToken);
            FCommand = null;
        } else {
            new NGScriptTokenParameter(FCommand, aToken);
        }
    }

    public NGScriptParser() {
        super();
    }

}
