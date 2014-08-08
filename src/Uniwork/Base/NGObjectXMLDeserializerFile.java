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

    public NGObjectXMLDeserializerFile(Object aTarget) {
        this(aTarget, "");
    }

    public NGObjectXMLDeserializerFile(Object aTarget, String aFilename) {
        super(aTarget);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

    public String getFilename() {
        return FFilename;
    }

}
