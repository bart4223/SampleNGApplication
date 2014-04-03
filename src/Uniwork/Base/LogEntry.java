package Uniwork.Base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LogEntry {

    protected Date FDate;
    protected String FText;

    public LogEntry() {
        this("");
    }

    public LogEntry(String aText) {
        this(new Date(), aText);
    }

    public LogEntry(Date aDate, String aText) {
        FDate = aDate;
        FText = aText;
    }

    public Date GetDate() {
        return FDate;
    }

    public String GetText() {
        return FText;
    }

    public String GetDateAsString() {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
        return formatter.format(FDate);
    }

}
