package Uniwork.Base;

import java.io.FileWriter;
import java.io.Writer;

public class NGObjectJSONSerializerFile extends NGObjectJSONSerializer {

    protected String FFilename;

    @Override
    protected void Close() {
        super.Close();
        writeLog(String.format("Save file %s successfully.", FFilename));
    }

    @Override
    protected void DoWriteObject() {
        super.DoWriteObject();
        try {
            Writer writer = new FileWriter(FFilename);
            writer.write(FJSON);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {

        }
    }

    public NGObjectJSONSerializerFile(Object aSource, String aFilename) {
        this(aSource, null, aFilename);
    }

    public NGObjectJSONSerializerFile(Object aSource, Class aTargetClass, String aFilename) {
        super(aSource, aTargetClass);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

    public String getFilename() {
        return FFilename;
    }

}
