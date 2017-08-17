package Uniwork.Base;

public class NGScriptParser extends NGTextParser {

    public String CRemark = "//";
    public String CAllocation = "=>";

    protected NGParseTokenCommand FCommand = null;

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
            new NGParseTokenRemark(FParseTree.getRoot(), aSpecialToken);
        }
    }

    @Override
    protected void DoTokenFound(String aToken) {
        if (FCommand == null) {
            FCommand = new NGParseTokenCommand(FParseTree.getRoot(), aToken);
        } else if (!aToken.equals(CAllocation)){
            new NGParseTokenParameter(FCommand, aToken);
        }
    }

    public NGScriptParser() {
        super();
    }

}
