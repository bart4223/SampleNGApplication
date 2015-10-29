package Uniwork.Test;

import Uniwork.Misc.NGConsoleLogEventListener;
import Uniwork.Misc.NGLogEntry;

public class NGTeamcityLogEventListener extends NGConsoleLogEventListener {

    @Override
    protected void writeLog(NGLogEntry aLogEntry) {
        writeConsole(String.format("##teamcity[%s]", aLogEntry.GetText()));
    }

    public NGTeamcityLogEventListener() {
        super();
    }

}
