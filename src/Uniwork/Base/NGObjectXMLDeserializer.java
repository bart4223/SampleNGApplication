package Uniwork.Base;

import java.beans.XMLDecoder;

public class NGObjectXMLDeserializer extends NGObjectDeserializer {

    protected XMLDecoder FDecoder;

    @Override
    protected void CreateDecoder() throws Exception {
        FDecoder = new java.beans.XMLDecoder(FInputStream);
    }

    @Override
    protected void Close() {
        FDecoder.close();
    }

    @Override
    protected void DoReadObject() {
        FSource = (Object)FDecoder.readObject();
    }

    public NGObjectXMLDeserializer(Object aTarget) {
        super(aTarget);
    }

}
