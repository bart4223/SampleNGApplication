package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

import java.io.InputStream;

public abstract class NGObjectDeserializer extends NGObject implements NGObjectDeserialization {

    protected Object FTarget;
    protected Object FSource;
    protected NGLogManager FLogManager;
    protected InputStream FInputStream;

    protected void CreateInputStream() throws Exception {

    }

    protected void CreateDecoder() throws Exception {

    }

    protected void Open() throws Exception{
        CreateInputStream();
        CreateDecoder();
    }

    protected void Close() {

    }

    protected void DoTransform() {
        if (FTarget != null) {
            NGObjectTransformation transform = (NGObjectTransformation)FTarget;
            transform.AssignFrom(FSource);
        }
        else {
            FTarget = FSource;
        }
    }

    protected void DoReadObject() {

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
                writeLog(5, String.format("Object [%s] successfully deserialized.", FSource.getClass().getName()));
            }
            finally {
                Close();
            }
        }
        catch (Exception e) {
            writeError("InternalDeserialize", e.getMessage());
        }
    }

    protected void writeError(String aMethodName, String aErrorText) {
        writeLog(0, String.format("<<<ERROR>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aErrorText));
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    public NGObjectDeserializer(Object aTarget) {
        FTarget = aTarget;
        FSource = null;
        FInputStream = null;
        FLogManager = null;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void setTarget(Object aObject) {
        FTarget = aObject;
    }

    public Object getTarget() {
        return FTarget;
    }

    @Override
    public void deserializeObject() {
        InternalDeserialize();
    }

}
