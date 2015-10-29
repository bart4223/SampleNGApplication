package Uniwork.Misc;

import Uniwork.Base.NGObject;

public class NGConsoleLogEventListener extends NGObject implements NGLogEventListener {

    protected void writeConsole(String aText) {
        System.out.println(aText);
    }

    protected void writeLog(NGLogEntry aLogEntry) {
        writeConsole(aLogEntry.GetFullAsString());
    }

    public NGConsoleLogEventListener() {
        super();
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        writeLog(e.LogEntry);
    }

    @Override
    public void handleClearLog() {

    }

}
