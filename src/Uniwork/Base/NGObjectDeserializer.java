package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

import java.beans.XMLDecoder;
import java.io.InputStream;

public abstract class NGObjectDeserializer extends NGObject implements NGObjectDeserialization {

    protected XMLDecoder FDecoder;
    protected NGObject FObject;
    protected NGObject FXMLObject;
    protected NGLogManager FLogManager;
    protected InputStream FInputStream;

    protected void CreateInputStream() throws Exception{

    }

    protected void Open() throws Exception{
        CreateInputStream();
        FDecoder = new java.beans.XMLDecoder(FInputStream);
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

    protected void DoDeserialize() {
        DoReadObject();
        DoTransform();
    }

    protected void InternalDeserialize() {
        try {
            Open();
            try {
                DoDeserialize();
                writeLog(5, String.format("Object [%s] successfully deserialized.", FObject.getClass().getName()));
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

    public NGObjectDeserializer() {
        this(null);
    }

    public NGObjectDeserializer(NGObject aObject) {
        FObject = aObject;
        FXMLObject = null;
        FInputStream = null;
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
    public void deserializeObject() {
        InternalDeserialize();
    }

}
