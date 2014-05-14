package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;

public class NGObjectSerializer extends NGObject {

    protected FileOutputStream FOutput;
    protected XMLEncoder FEncoder;
    protected NGObject FObject;
    protected NGObject FXMLObject;
    protected NGLogManager FLogManager;

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
        FXMLObject = FObject.AssignTo();
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    public NGObjectSerializer(String aFilename, NGObject aObject) throws Exception {
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

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

}
