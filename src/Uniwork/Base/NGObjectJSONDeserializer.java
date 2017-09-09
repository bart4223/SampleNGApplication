package Uniwork.Base;

import com.google.gson.Gson;

public class NGObjectJSONDeserializer extends NGObjectDeserializer {

    protected Gson FGSON;
    protected String FJSON;

    @Override
    protected void DoReadObject() {
        FSource = FGSON.fromJson(FJSON, FTargetClass);
    }

    public NGObjectJSONDeserializer(Class aTargetClass) {
        this(null, aTargetClass);
    }

    public NGObjectJSONDeserializer(Object aTarget, Class aTargetClass) {
        super(aTarget, aTargetClass);
        FGSON = new Gson();
        FJSON = null;
    }

    public void setJSON(String aJSON) {
        FJSON = aJSON;
    }

}
