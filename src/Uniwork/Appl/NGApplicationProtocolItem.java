package Uniwork.Appl;

import Uniwork.Misc.NGCustomLogEntry;

import java.util.Date;

public class NGApplicationProtocolItem extends NGCustomLogEntry {

    protected String FHint;
    protected String FSubDomain;

    public NGApplicationProtocolItem(String aSubDomain, String aText, String aHint, LogType aType) {
        super(new Date(), aText, "", aType);
        FHint = aHint;
        FSubDomain = aSubDomain;
    }

    public String getHint() {
        return FHint;
    }

    public String getSubDomain() {
        return FSubDomain;
    }
    
}
