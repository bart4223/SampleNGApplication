package Uniwork.Base;

import java.io.FileInputStream;

public class NGObjectJSONDeserializerFile extends NGObjectJSONDeserializer {

    protected String FFilename;

    @Override
    protected void CreateInputStream() throws Exception {
        FInputStream = new FileInputStream(FFilename);
        FJSON = "";
        try {
            int lContent;
            while ((lContent = FInputStream.read()) != -1) {
                FJSON = FJSON + (char)lContent;
            }
        } finally {
            FInputStream.close();
        }
    }

    @Override
    protected void Close() {
        super.Close();
        writeLog(String.format("Load file %s successfully.", FFilename));
    }

    public NGObjectJSONDeserializerFile(Class aTargetClass) {
        this(null, aTargetClass, "");
    }

    public NGObjectJSONDeserializerFile(Object aTarget, Class aTargetClass, String aFilename) {
        super(aTarget, aTargetClass);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

    public String getFilename() {
        return FFilename;
    }

}
