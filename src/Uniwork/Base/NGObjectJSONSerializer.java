package Uniwork.Base;

import com.google.gson.Gson;

public class NGObjectJSONSerializer extends NGObjectSerializer {

    protected Gson FGSON;
    protected String FJSON;

    @Override
    protected void DoWriteObject() {
        FJSON = FGSON.toJson(FTarget);
    }

    public NGObjectJSONSerializer(Object aSource) {
        this(aSource, null);
    }

    public NGObjectJSONSerializer(Object aSource, Class aTargetClass) {
        super(aSource, aTargetClass);
        FGSON = new Gson();
    }

    public String getJSON() {
        return FJSON;
    }

}
