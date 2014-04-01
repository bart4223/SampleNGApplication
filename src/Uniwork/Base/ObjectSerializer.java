package Uniwork.Base;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;

public class ObjectSerializer {

    protected FileOutputStream FOutput;
    protected XMLEncoder FEncoder;
    protected Object FObject;
    protected Object FXMLObject;

    protected void DoSerialize() {

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
        DoSerialize();
        FEncoder.writeObject(FXMLObject);
        FEncoder.flush();
        FEncoder.close();
    }

}
