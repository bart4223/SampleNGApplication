package Uniwork.Base;

import java.beans.XMLDecoder;
import java.io.FileInputStream;

public class ObjectDeserializer {

    protected FileInputStream FInput;
    protected XMLDecoder FDecoder;
    protected Object FObject;
    protected Object FXMLObject;

    protected void DoDeserialize() {

    }

    public ObjectDeserializer(String aFilename, Object aObject) throws Exception {
        FInput = new FileInputStream(aFilename);
        FDecoder = new java.beans.XMLDecoder(FInput);
        FObject = aObject;
        FXMLObject = null;
    }

    public void Deserialize() throws Exception {
        FXMLObject = FDecoder.readObject();
        DoDeserialize();
        FDecoder.close();
    }

}
