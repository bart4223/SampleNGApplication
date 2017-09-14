package Uniwork.Misc;

import Uniwork.Base.NGObject;
import Uniwork.Base.NGObjectJSONSerializer;

public class NGLogObject extends NGObject {

    public static class SimpleMessage {
        
        public SimpleMessage(String aMessage) {
            Classname = getClass().getName();
            Message = aMessage;
        }

        public String Classname;
        public String Message;

    }

    public static class ColorMessage extends SimpleMessage {

        public ColorMessage(String aMessage, String aColor) {
            super(aMessage);
            Color = aColor;
        }

        public String Color;

    }

    public static class ScriptMessage extends ColorMessage {

        public ScriptMessage(String aMessage, String aScriptCaption, String aScript) {
            this(aMessage, "000000", aScriptCaption, aScript);
        }

        public ScriptMessage(String aMessage, String aColor, String aScriptCaption, String aScript) {
            super(aMessage, aColor);
            Script = aScript;
            ScriptCaption = aScriptCaption;
        }

        public String Script;
        public String ScriptCaption;

    }

    protected NGObjectJSONSerializer FJSON;
    protected Object FSource;

    public static String CreateSimpleMessage(String aMessage) {
        return CreateLogMessage(new NGLogObject.SimpleMessage(aMessage));
    }

    public static String CreateColorMessage(String aMessage, String aColor) {
        return CreateLogMessage(new NGLogObject.ColorMessage(aMessage, aColor));
    }

    public static String CreateScriptMessage(String aMessage, String aScript) {
        return CreateLogMessage(new NGLogObject.ScriptMessage(aMessage, "...", aScript));
    }

    public static String CreateScriptMessage(String aMessage, String aScriptCaption, String aScript) {
        return CreateLogMessage(new NGLogObject.ScriptMessage(aMessage, aScriptCaption, aScript));
    }

    public static String CreateScriptMessage(String aMessage, String aColor, String aScriptCaption, String aScript) {
        return CreateLogMessage(new NGLogObject.ScriptMessage(aMessage, aColor, aScriptCaption, aScript));
    }

    public static String CreateLogMessage(Object aSource) {
        NGLogObject lo = new NGLogObject(aSource);
        return lo.getJSON();
    }

    public NGLogObject(Object aSource) {
        super();
        FJSON = new NGObjectJSONSerializer(null);
        FSource = aSource;
    }

    public String getJSON() {
        FJSON.setSource(FSource);
        FJSON.serializeObject();
        return FJSON.getJSON();
    }

}
