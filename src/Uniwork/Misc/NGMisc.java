package Uniwork.Misc;

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

}
