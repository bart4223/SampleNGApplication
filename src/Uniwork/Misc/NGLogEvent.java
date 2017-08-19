package Uniwork.Misc;

import java.util.EventObject;

public class NGLogEvent extends EventObject {

    public NGLogEntry LogEntry;

    public NGLogEvent(Object source) {
        this(source, null);
    }

    public NGLogEvent(Object source, NGLogEntry aLogEntry) {
        super(source);
        LogEntry = aLogEntry;
    }

}
