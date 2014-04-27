package Uniwork.Base;

import java.beans.XMLDecoder;
import java.io.FileInputStream;

public class ObjectDeserializer {

    protected FileInputStream FInput;
    protected XMLDecoder FDecoder;
    protected Object FObject;
    protected Object FXMLObject;

    protected void Open() {

    }

    protected void Close() {
        FDecoder.close();
    }

    protected void DoTransform() {

    }

    protected void DoReadObject() {
        FXMLObject = FDecoder.readObject();
    }

    public ObjectDeserializer(String aFilename, NGObject aObject) throws Exception {
        FInput = new FileInputStream(aFilename);
        FDecoder = new java.beans.XMLDecoder(FInput);
        FObject = aObject;
        FXMLObject = null;
    }

    public void Deserialize() throws Exception {
        Open();
        DoReadObject();
        DoTransform();
        Close();
    }

}
