package Uniwork.Base;

import java.io.FileInputStream;

public class NGObjectDeserializerFile extends NGObjectDeserializer {

    protected String FFilename;

    @Override
    protected void CreateInputStream() throws Exception {
        FInputStream = new FileInputStream(FFilename);
    }

    @Override
    protected void Close() {
        super.Close();
        writeLog(String.format("Load file %s successfully.", FFilename));
    }

    public NGObjectDeserializerFile() {
        this(null);
    }

    public NGObjectDeserializerFile(NGObject aObject) {
        this(aObject, "");
    }

    public NGObjectDeserializerFile(NGObject aObject, String aFilename) {
        super(aObject);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

}
