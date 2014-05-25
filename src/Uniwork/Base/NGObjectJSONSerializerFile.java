package Uniwork.Base;

import java.io.FileOutputStream;

public class NGObjectJSONSerializerFile extends NGObjectJSONSerializer {

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

    public NGObjectJSONSerializerFile(Object aObject, String aFilename) {
        super(aObject);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

    public String getFilename() {
        return FFilename;
    }

}
