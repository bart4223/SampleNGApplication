package Uniwork.Script;

import Uniwork.Base.NGTextParser;

public class NGScriptParser extends NGTextParser {

    public String CRemark = "//";
    public String CAllocation = "=>";

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
    protected void DoSpecialTokenFound(String aSpecialStart, String aSpecialToken) {
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
        } else {
            new NGScriptTokenParameter(FCommand, aToken);
        }
    }

    public NGScriptParser() {
        super();
    }

}
