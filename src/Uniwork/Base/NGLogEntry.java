package Uniwork.Base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NGLogEntry {

    protected Date FDate;
    protected String FText;

    public NGLogEntry() {
        this("");
    }

    public NGLogEntry(String aText) {
        this(new Date(), aText);
    }

    public NGLogEntry(Date aDate, String aText) {
        FDate = aDate;
        FText = aText;
    }

    public Date GetDate() {
        return FDate;
    }

    public String GetText() {
        return FText;
    }

    public String GetDateAsString(String aFormat) {
        DateFormat formatter = new SimpleDateFormat(aFormat);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
        return formatter.format(FDate);
    }

    public String GetFullAsString() {
        return GetFullAsString("HH:mm:ss");
    }

    public String GetFullAsString(String aFormat) {
        return GetDateAsString(aFormat) + " " + GetText();
    }

}
