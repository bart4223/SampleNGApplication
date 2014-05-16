package Uniwork.Base;

import java.io.FileOutputStream;

public class NGObjectSerializerFile extends NGObjectSerializer {

    protected String FFilename;

    @Override
    protected void CreateOutputStream() throws Exception {
        FOutputStream = new FileOutputStream(FFilename);
    }

    @Override
    protected void Close() {
        super.Close();
        writeLog(String.format("Saving file %s successfully.", FFilename));
    }

    public NGObjectSerializerFile() {
        this(null);
    }

    public NGObjectSerializerFile(NGObject aObject) {
        this(aObject, "");
    }

    public NGObjectSerializerFile(NGObject aObject, String aFilename) {
        super(aObject);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

}
