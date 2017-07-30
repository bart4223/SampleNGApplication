package Uniwork.UI;

import Uniwork.Base.NGObject;

public class NGUIImageModificationToolboxContext extends NGObject {

    protected String FFilename;

    public NGUIImageModificationToolboxContext(String aFilename) {
        super();
        FFilename = aFilename;
    }

    public String getFilename() {
        return FFilename;
    }

}
