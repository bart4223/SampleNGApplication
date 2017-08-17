package Uniwork.Base;

import java.util.ArrayList;

public class NGTextParser extends NGComponent {

    protected Integer FTokenCount;
    protected NGParseTree FParseTree;
    protected NGObjectQueue FTokenQueue;
    protected ArrayList<String> FSpecialStarts;

    protected void DoSpecialTokenFound(String aSpecialStart, String aSpecialToken) {

    }

    protected void _SpecialTokenFound(String aSpecialStart, String aSpecialToken) {
        FTokenCount = FTokenCount + 1;
        DoSpecialTokenFound(aSpecialStart, aSpecialToken);
    }

    protected void DoTokenFound(String aToken) {

    }

    protected void _TokenFound(String aToken) {
        FTokenCount = FTokenCount + 1;
        DoTokenFound(aToken);
    }

    protected void DoBeforeParse() {
        FTokenCount = 0;
        FParseTree.RemoveAll();
        FTokenQueue.clear();
    }

    protected void _Parse(String aText) {
        for (int i = 0; i < FSpecialStarts.size(); i++) {
            if (aText.startsWith(FSpecialStarts.get(i))) {
                _SpecialTokenFound(FSpecialStarts.get(i), aText);
                return;
            }
        }
        DoParse(aText);
    }

    protected void DoParse(String aText) {
        String token = "";
        for (int i = 0; i < aText.length(); i++) {
            char c =  aText.charAt(i);
            Boolean InToken = (c != ' ');
            if (InToken) {
                token = token + c;
            } else  {
                if (token.length() > 0) {
                    _TokenFound(token);
                    token = "";
                }
            }

        }
        if (token.length() > 0) {
            _TokenFound(token);
        }
    }

    protected void DoAfterParse() {

    }

    public NGTextParser() {
        super();
        FParseTree = new NGParseTree();
        FTokenQueue = new NGObjectQueue();
        FSpecialStarts = new ArrayList<String>();
    }

    public void Parse(String aText) {
        DoBeforeParse();
        try {
            _Parse(aText);
        } finally {
            DoAfterParse();
        }
    }

    public Integer getTokenCount() {
        return FTokenCount;
    }

    public void addSpecialStart(String aSpecialStart) {
        FSpecialStarts.add(aSpecialStart);
    }

    public NGParseTree getParseTree() {
        return FParseTree;
    }

}
