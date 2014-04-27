package Uniwork.Base;

import java.util.EventObject;

public class NGLogEvent extends EventObject {

    public NGLogEntry LogEntry;

    public NGLogEvent(Object source) {
        super(source);
    }

    public NGLogEvent(Object source, NGLogEntry aLogEntry) {
        this(source);
        LogEntry = aLogEntry;
    }

}
