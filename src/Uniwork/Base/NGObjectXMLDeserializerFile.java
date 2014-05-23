package Uniwork.Base;

import java.io.FileInputStream;

public class NGObjectXMLDeserializerFile extends NGObjectXMLDeserializer {

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

    public NGObjectXMLDeserializerFile(Object aObject) {
        this(aObject, "");
    }

    public NGObjectXMLDeserializerFile(Object aObject, String aFilename) {
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
