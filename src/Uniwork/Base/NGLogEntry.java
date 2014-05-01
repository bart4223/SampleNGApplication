package Uniwork.Base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NGLogEntry {

    protected Date FDate;
    protected String FText;
    protected String FSource;

    public NGLogEntry() {
        this("");
    }

    public NGLogEntry(String aText) {
        this(new Date(), aText);
    }

    public NGLogEntry(Date aDate, String aText) {
        this(aDate, aText, "");
    }

    public NGLogEntry(Date aDate, String aText, String aSource) {
        FDate = aDate;
        FText = aText;
        FSource = aSource;
    }

    public Date GetDate() {
        return FDate;
    }

    public String GetText() {
        return FText;
    }

    public String GetSource() {
        return FSource;
    }

    public String GetDateAsString(String aFormat) {
        DateFormat formatter = new SimpleDateFormat(aFormat);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
        return formatter.format(FDate);
    }

    public String GetFullAsString() {
        return GetFullAsString("HH:mm:ss", true);
    }

    public String GetFullAsString(String aFormat) {
        return GetFullAsString("HH:mm:ss", true);
    }

    public String GetFullAsString(Boolean aWithSource) {
        return GetFullAsString("HH:mm:ss", aWithSource);
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
