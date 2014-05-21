package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

import java.beans.XMLEncoder;
import java.io.OutputStream;

public abstract class NGObjectSerializer extends NGObject implements NGObjectSerialization {

    protected XMLEncoder FEncoder;
    protected NGObject FObject;
    protected NGObject FXMLObject;
    protected NGLogManager FLogManager;
    protected OutputStream FOutputStream;

    protected void CreateOutputStream() throws Exception{

    }

    protected void Open() throws Exception{
        CreateOutputStream();
        FEncoder = new java.beans.XMLEncoder(FOutputStream);
    }

    protected void Close() {
        FEncoder.flush();
        FEncoder.close();
    }

    protected void DoTransform() {
        FXMLObject = FObject.AssignTo();
    }

    protected void DoWriteObject() {
        FEncoder.writeObject(FXMLObject);
    }

    protected void DoSerialize() {
        DoTransform();
        DoWriteObject();
    }

    protected void InternalSerialize() {
        try {
            Open();
            try {
                DoSerialize();
                writeLog(5, String.format("Object [%s] successfully serialized.", FObject.getClass().getName()));
            }
            finally {
                Close();
            }
        }
        catch (Exception e) {
            writeLog(e.getMessage());
        }
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    public NGObjectSerializer() {
        this(null);
    }

    public NGObjectSerializer(NGObject aObject) {
        FObject = aObject;
        FXMLObject = null;
        FOutputStream = null;
        FLogManager = null;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void setObject(NGObject aObject) {
        FObject = aObject;
    }

    public NGObject getObject() {
        return FObject;
    }

    @Override
    public void Serialize() {
        InternalSerialize();
    }

}
