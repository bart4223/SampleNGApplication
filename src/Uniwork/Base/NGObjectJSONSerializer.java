package Uniwork.Base;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

public class NGObjectJSONSerializer extends NGObjectSerializer {

    protected JSON FJSON;

    @Override
    protected void DoWriteObject() {
        //JSONSerializer.toJSON(FTarget);
        System.out.println(FTarget.getClass().getName());
        //FJSON = JSONSerializer.toJSON(FTarget);
        //FJSON.write();
    }

    public NGObjectJSONSerializer(Object aObject) {
        super(aObject, null);
    }

}
