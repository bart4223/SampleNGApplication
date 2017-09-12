package Uniwork.Misc;

import Uniwork.Base.NGObjectJSONDeserializer;
import com.google.gson.JsonElement;

import java.util.Date;

public class NGLogEntry {

    public final static String FMT_STD_DATE = "dd.MM.YYYY HH:mm:ss";

    public enum LogType{Info, Warning, Error}

    protected Date FDate;
    protected String FText;
    protected String FSource;
    protected LogType FType;
    protected JsonElement FJSONRoot;
    protected NGObjectJSONDeserializer FJSONDeserializer;

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
        FJSONDeserializer = new NGObjectJSONDeserializer(JsonElement.class);
        FDate = aDate;
        FText = aText;
        FJSONDeserializer.setJSON(FText);
        if (FJSONDeserializer.deserializeObject()) {
            FJSONRoot = (JsonElement)FJSONDeserializer.getTarget();
        }
        FSource = aSource;
        FType = aType;
    }

    public Date GetDate() {
        return FDate;
    }

    public String GetText() {
        String res = FText;
        if (IsEncapsulated()) {
            res = FJSONRoot.getAsJsonObject().get("Message").getAsString();
        }
        return res;
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

    public Boolean IsEncapsulated() {
        return FJSONRoot != null && FJSONRoot.isJsonObject();
    }

    public String getJSON() {
        return FText;
    }

    public String getJSONClass() {
        String res = "";
        if (IsEncapsulated()) {
            res = FJSONRoot.getAsJsonObject().get("Classname").getAsString();
        }
        return res;
    }

}
