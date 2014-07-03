package Uniwork.Base;

import Uniwork.Misc.NGLogManager;

import java.io.OutputStream;

public abstract class NGObjectSerializer extends NGObject implements NGObjectSerialization {

    protected Object FSource;
    protected Object FTarget;
    protected NGLogManager FLogManager;
    protected OutputStream FOutputStream;
    protected Class FTargetClass;

    protected void CreateEncoder() throws Exception {

    }

    protected void CreateOutputStream() throws Exception {

    }

    protected void CreateTarget() throws Exception {
        FTarget = FTargetClass.getConstructor().newInstance();
    }

    protected void Open() throws Exception{
        CreateOutputStream();
        CreateEncoder();
        if (FTargetClass != null) {
            CreateTarget();
        }
        else {
            FTarget = FSource;
        }
    }

    protected void Close() {

    }

    protected void DoTransform() {
        NGObjectTransformation transform = (NGObjectTransformation)FSource;
        transform.AssignTo(FTarget);
    }

    protected void DoWriteObject() {

    }

    protected void DoSerialize() {
        if (FTargetClass != null) {
            DoTransform();
        }
        DoWriteObject();
    }

    protected void InternalSerialize() {
        try {
            Open();
            try {
                DoSerialize();
                writeLog(5, String.format("Object [%s] successfully serialized.", FSource.getClass().getName()));
            }
            finally {
                Close();
            }
        }
        catch (Exception e) {
            writeError("InternalSerialize", e.getMessage());
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

    public NGObjectSerializer(Object aSource, Class aTargetClass) {
        FSource = aSource;
        FTarget = null;
        FOutputStream = null;
        FLogManager = null;
        FTargetClass = aTargetClass;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void setSource(Object aSource) {
        FSource = aSource;
    }

    public Object getSource() {
        return FSource;
    }

    public void setTargetClass(Class aClass) {
        FTargetClass = aClass;
    }

    public Class getTragetClass() {
        return FTargetClass;
    }

    @Override
    public void serializeObject() {
        InternalSerialize();
    }

}
