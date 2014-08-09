package Uniwork.Misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class NGStrings {

    public static String addString(String aString1, String aString2, String aSeparator) {
        if (aString1.length() > 0) {
            return aString1 + aSeparator + aString2;
        }
        else {
            return aString2;
        }
    }

    public static String getDateAsString(Date aDate, String aFormat) {
        DateFormat formatter = new SimpleDateFormat(aFormat);
        formatter.setTimeZone(TimeZone.getDefault()); //TimeZone.getTimeZone("GMT+2:00")
        return formatter.format(aDate);
    }

    public static String getStringPos(String aValue, String aRegex, Integer aPosition) {
        // Regex Examples
        // "." use "\\."
        // Position from 1..
        String[] strs = aValue.split(aRegex);
        if (strs.length > aPosition - 1) {
            return strs[aPosition - 1];
        }
        else
            return "";
    }

}
