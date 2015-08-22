package Uniwork.Appl;

import Uniwork.Base.NGObject;

public class NGHelpContext extends NGObject {

    protected String FText;

    public NGHelpContext(String aText) {
        super();
        FText = aText;
    }

    public String getText() {
        return FText;
    }

}
