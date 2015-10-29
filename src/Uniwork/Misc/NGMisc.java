package Uniwork.Misc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

public final class NGMisc {

    public static Double Min(Double aA, Double aB) {
        if (aA <= aB)
            return aA;
        else
            return aB;
    }

    public static Double Max(Double aA, Double aB) {
        if (aA >= aB)
            return aA;
        else
            return aB;
    }

    public static String combinePath(String aPath1, String aPath2) {
        return Paths.get(aPath1, aPath2).toString();
    }

    public static String LoadFileContent(String aFilename) {
        String lResult = "";
        try {
            InputStream lFileStream = new FileInputStream(aFilename);
            try {
                if (lFileStream != null) {
                    int lContent;
                    while ((lContent = lFileStream.read()) != -1) {
                        lResult = lResult + (char)lContent;
                    }
                }
            } finally {
                if (lFileStream != null) {
                    lFileStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lResult;
    }

    public static StackTraceElement getCallStackElementByIndex(int aIndex) {
        int i = aIndex;
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (i == 0)
                return element;
            i--;
        }
        return null;
    }

    public static StackTraceElement getCurrentCallStackElement() {
        return getCallStackElementByIndex(3);
    }

    public static StackTraceElement getPrevCallStackElement() {
        return getCallStackElementByIndex(4);
    }

    public static StackTraceElement getLastCallStackElement() {
        return getCallStackElementByIndex(0);
    }

    public static StackTraceElement getFirstCallStackElement() {
        return getCallStackElementByIndex(Thread.currentThread().getStackTrace().length);
    }

}
