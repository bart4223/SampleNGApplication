package Uniwork.Misc;

import java.util.Date;

public class NGLogEntry extends NGCustomLogEntry {

    public NGLogEntry() {
        this("");
    }

    public NGLogEntry(String aText) {
        this(new Date(), aText);
    }

    public NGLogEntry(String aText, LogType aType) {
        this(aText, aType, "");
    }

    public NGLogEntry(String aText, LogType aType, String aSource) {
        this(new Date(), aText, aSource, aType);
    }

    public NGLogEntry(Date aDate, String aText) {
        this(aDate, aText, LogType.Info);
    }

    public NGLogEntry(Date aDate, String aText, LogType aType) {
        this(aDate, aText, "", aType);
    }

    public NGLogEntry(Date aDate, String aText, String aSource) {
        this(aDate, aText, aSource, LogType.Info);
    }

    public NGLogEntry(Date aDate, String aText, String aSource, LogType aType) {
        super(aDate, aText, aSource, aType);
    }

}
