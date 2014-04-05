package Uniwork.Base;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;

public class ObjectSerializer {

    protected FileOutputStream FOutput;
    protected XMLEncoder FEncoder;
    protected Object FObject;
    protected Object FXMLObject;

    protected void Open() {

    }

    protected void Close() {
        FEncoder.flush();
        FEncoder.close();
    }

    protected void DoWriteObject() {
        FEncoder.writeObject(FXMLObject);
    }

    protected void DoTransform() {

    }

    protected void setXMLObject(Object aXMLObject) {
        FXMLObject = aXMLObject;
    }

    public ObjectSerializer(String aFilename, Object aObject) throws Exception {
        FOutput = new FileOutputStream(aFilename);
        FEncoder = new java.beans.XMLEncoder(FOutput);
        FObject = aObject;
        FXMLObject = null;
    }

    public void Serialize() throws Exception {
        Open();
        DoTransform();
        DoWriteObject();
        Close();
    }

}
