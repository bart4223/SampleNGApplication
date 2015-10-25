package Uniwork.Misc;

import Uniwork.Base.NGObject;

public class NGConsoleLogEventListener extends NGObject implements NGLogEventListener {

    public enum mode {full, teamcity}

    protected mode FMode;

    public NGConsoleLogEventListener(mode aMode) {
        super();
        FMode = aMode;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        NGLogEntry log = e.LogEntry;
        switch (FMode) {
            case full:
                System.out.println(log.GetFullAsString());
                break;
            case teamcity:
                System.out.println(String.format("##teamcity[%s]", log.GetText()));
                break;
        }
    }

    @Override
    public void handleClearLog() {

    }
}
