package Uniwork.UI;

import Uniwork.Base.NGObject;

public class NGUIHelpToolboxContext extends NGObject {

    protected String FText;

    public NGUIHelpToolboxContext(String aText) {
        super();
        FText = aText;
    }

    public String getText() {
        return FText;
    }

}
