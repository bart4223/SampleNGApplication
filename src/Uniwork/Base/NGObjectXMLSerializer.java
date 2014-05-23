package Uniwork.Base;

import java.beans.XMLEncoder;

public class NGObjectXMLSerializer extends NGObjectSerializer {

    protected XMLEncoder FEncoder;

    @Override
    protected void CreateEncoder() throws Exception {
        FEncoder = new java.beans.XMLEncoder(FOutputStream);
    }

    @Override
    protected void Close() {
        FEncoder.flush();
        FEncoder.close();
    }

    @Override
    protected void DoWriteObject() {
        FEncoder.writeObject(FTarget);
    }

    public NGObjectXMLSerializer(Object aObject, Class aTargetClass) {
        super(aObject, aTargetClass);
    }

}
