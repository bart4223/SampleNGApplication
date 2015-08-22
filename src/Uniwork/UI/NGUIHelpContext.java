package Uniwork.UI;

import Uniwork.Base.NGObject;

public class NGUIHelpContext extends NGObject {

    protected String FText;

    public NGUIHelpContext(String aText) {
        super();
        FText = aText;
    }

    public String getText() {
        return FText;
    }

}
