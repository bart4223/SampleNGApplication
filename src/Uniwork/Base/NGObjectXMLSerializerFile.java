package Uniwork.Base;

import java.io.FileOutputStream;

public class NGObjectXMLSerializerFile extends NGObjectXMLSerializer {

    protected String FFilename;

    @Override
    protected void CreateOutputStream() throws Exception {
        FOutputStream = new FileOutputStream(FFilename);
    }

    @Override
    protected void Close() {
        super.Close();
        writeLog(String.format("Save file %s successfully.", FFilename));
    }

    public NGObjectXMLSerializerFile(Object aObject, Class aTargetClass) {
        this(aObject, aTargetClass, "");
    }

    public NGObjectXMLSerializerFile(Object aObject, Class aTargetClass, String aFilename) {
        super(aObject, aTargetClass);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

    public String getFilename() {
        return FFilename;
    }

}
