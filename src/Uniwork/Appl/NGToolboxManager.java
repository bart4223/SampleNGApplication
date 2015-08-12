package Uniwork.Appl;

import Uniwork.Base.NGComponent;

public class NGToolboxManager extends NGStageManager {

    public NGToolboxManager() {
        this("");
    }

    public NGToolboxManager(String aName) {
        this(null, aName);
    }

    public NGToolboxManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
    }

}
