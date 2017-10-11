package Uniwork.UI;

import Uniwork.Appl.NGApplicationProtocol;
import Uniwork.Base.NGObject;

public class NGUIApplicationProtocolContext extends NGObject {

    protected NGApplicationProtocol FApplicationProtocol;

    public NGUIApplicationProtocolContext(NGApplicationProtocol aApplicationProtocol) {
        super();
        FApplicationProtocol = aApplicationProtocol;
    }

    public NGApplicationProtocol getApplicationProtocol() {
        return FApplicationProtocol;
    }

}
