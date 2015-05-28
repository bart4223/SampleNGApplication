package Uniwork.Appl;

import Uniwork.Base.NGComponent;

public abstract class NGCustomApplicationModule extends NGComponent {

    protected String FDescription = "";

    public NGCustomApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName);
        FDescription = aDescription;
    }

    public String getDescription() {
        return FDescription;
    }

}
