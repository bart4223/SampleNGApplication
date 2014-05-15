package Uniwork.Base;

import Uniwork.Base.NGObject;
import Uniwork.Base.NGObjectSerializer;

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
        writeLog(String.format("Saving file %s successfully...", FFilename));

    }

    public NGObjectSerializerFile(NGObject aObject, String aFilename) {
        super(aObject);
        FFilename = aFilename;
    }

    public void setFilename(String aFilename) {
        FFilename = aFilename;
    }

}
