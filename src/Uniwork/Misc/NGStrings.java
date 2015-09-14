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
        // "," use ","
        // "." use "\\."
        // Position from 1..
        String[] strs = aValue.split(aRegex);
        if (strs.length > aPosition - 1) {
            return strs[aPosition - 1];
        }
        else {
            return "";
        }
    }

    public static String getLastString(String aValue, String aSep) {
        String res = aValue;
        Integer i =  aValue.lastIndexOf(aSep);
        if ((i > 0) && (i + 1 < aValue.length()))
            res = aValue.substring(i + 1, aValue.length());
        return res;
    }

    public static String getFirstString(String aValue, String aSep) {
        String res = aValue;
        Integer i =  aValue.indexOf(aSep);
        if (i > 0)
            res = aValue.substring(0, i);
        return res;
    }

    public static Boolean IsStringInString(String aValue, String aStrings, String aRegex) {
        // Regex Examples
        // "," use ","
        // "." use "\\."
        String[] strs = aStrings.split(aRegex);
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(aValue)) {
                return true;
            }
        }
        return false;
    }

    public static String leftPad(String aValue, Integer aLength, String aChar) {
        String res = aValue;
        if (aValue.length() < aLength && aChar.length() == 1) {
            while (res.length() < aLength) {
                res = aChar + res;
            }
        }
        return res;
    }

}
