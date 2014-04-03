package Uniwork.Base;

import java.util.EventObject;

public class LogEvent extends EventObject {

    public LogEntry LogEntry;

    public LogEvent(Object source) {
        super(source);
    }

    public LogEvent(Object source, LogEntry aLogEntry ) {
        this(source);
        LogEntry = aLogEntry;
    }

}
