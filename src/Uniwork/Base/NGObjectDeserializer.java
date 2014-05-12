package Uniwork.Base;

import java.beans.XMLDecoder;
import java.io.FileInputStream;

public class NGObjectDeserializer extends NGObject{

    protected FileInputStream FInput;
    protected XMLDecoder FDecoder;
    protected NGObject FObject;
    protected NGObject FXMLObject;
    protected NGLogManager FLogManager;

    protected void Open() {

    }

    protected void Close() {
        FDecoder.close();
    }

    protected void DoTransform() {
        FObject.AssignFrom(FXMLObject);
    }

    protected void DoReadObject() {
        FXMLObject = (NGObject)FDecoder.readObject();
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    public NGObjectDeserializer(String aFilename, NGObject aObject) throws Exception {
        FInput = new FileInputStream(aFilename);
        FDecoder = new java.beans.XMLDecoder(FInput);
        FObject = aObject;
        FXMLObject = null;
        FLogManager = null;
    }

    public void Deserialize() throws Exception {
        Open();
        DoReadObject();
        DoTransform();
        Close();
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

}
