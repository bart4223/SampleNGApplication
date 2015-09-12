package Uniwork.Misc;

import java.util.Date;

public class NGLogEntry {

    public final static String FMT_STD_DATE = "dd.MM.YYYY HH:mm:ss";

    public enum LogType{Info, Warning, Error}

    protected Date FDate;
    protected String FText;
    protected String FSource;
    protected LogType FType;

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
        FDate = aDate;
        FText = aText;
        FSource = aSource;
        FType = aType;
    }

    public Date GetDate() {
        return FDate;
    }

    public String GetText() {
        return FText;
    }

    public LogType GetType() {
        return FType;
    }

    public String GetSource() {
        return FSource;
    }

    public String GetDateAsString() {
        return GetDateAsString(FMT_STD_DATE);
    }

    public String GetDateAsString(String aFormat) {
        return NGStrings.getDateAsString(FDate, aFormat);
    }

    public String GetFullAsString() {
        return GetFullAsString(FMT_STD_DATE, false);
    }

    public String GetFullAsString(String aFormat) {
        return GetFullAsString(FMT_STD_DATE, true);
    }

    public String GetFullAsString(Boolean aWithSource) {
        return GetFullAsString(FMT_STD_DATE, aWithSource);
    }

    public String GetFullAsString(String aFormat, Boolean aWithSource) {
        if (!aWithSource || FSource.length() == 0) {
            return GetDateAsString(aFormat) + " " + GetText();
        }
        else {
            return GetDateAsString(aFormat) + " " + GetSource() + " - " + GetText();
        }
    }

}
