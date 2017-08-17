package Uniwork.Base;

public class NGParseToken extends NGObjectNode {

    protected String FToken;

    public NGParseToken(NGObjectNode aParent, String aToken) {
        super(aParent);
        FToken = aToken;
    }

    public String getToken() {
        return FToken;
    }

}
