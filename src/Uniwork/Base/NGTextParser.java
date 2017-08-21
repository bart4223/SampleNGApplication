package Uniwork.Base;

import java.util.ArrayList;

public class NGTextParser extends NGComponent {

    public String CStringLimiter = "\"";

    protected ArrayList<String> FSpecialStarts;

    protected Integer FTokenCount;
    protected NGParseTree FParseTree;
    protected NGObjectQueue FTokenQueue;
    protected Boolean FInString = false;

    protected void DoSpecialStartsTokenFound(String aSpecialStart, String aSpecialToken) {

    }

    protected void _SpecialStartsTokenFound(String aSpecialStart, String aSpecialToken) {
        FTokenCount = FTokenCount + 1;
        DoSpecialStartsTokenFound(aSpecialStart, aSpecialToken);
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
        FInString = false;
    }

    protected void _Parse(String aText) {
        for (int i = 0; i < FSpecialStarts.size(); i++) {
            if (aText.startsWith(FSpecialStarts.get(i))) {
                _SpecialStartsTokenFound(FSpecialStarts.get(i), aText);
                return;
            }
        }
        DoParse(aText);
    }

    protected void DoParse(String aText) {
        String token = "";
        for (int i = 0; i < aText.length(); i++) {
            String Char = String.format("%c", aText.charAt(i));
            Boolean InToken = (!FInString && !Char.equals(" ")) || (FInString && !Char.equals(CStringLimiter));
            if (InToken) {
                token = String.format("%s%s",token, Char);
            } else  {
                if (token.length() > 0) {
                    if (!FInString && token.startsWith(CStringLimiter)) {
                        token = token.substring(1, token.length());
                        token = String.format("%s%s",token, Char);
                        FInString = true;
                    } else if (!FInString || FInString && Char.equals(CStringLimiter)) {
                        if (Char.equals(CStringLimiter)) {
                            FInString = false;
                        }
                        _TokenFound(token);
                        token = "";
                    }
                }
            }
        }
        if (token.length() > 0) {
            if (FInString && token.endsWith(CStringLimiter)) {
                token = token.substring(0, token.length() - 1);
            }
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
