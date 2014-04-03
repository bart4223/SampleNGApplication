package Uniwork.Base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LogManager {

    protected ArrayList<LogEntry> FItems;
    protected List FEventListeners;

    protected void addLog(LogEntry aLogEntry) {
        FItems.add(aLogEntry);
        raiseAddLogEvent(aLogEntry);
    }

    protected synchronized void raiseAddLogEvent(LogEntry aLogEntry) {
        LogEvent lEvent = new LogEvent(this, aLogEntry);
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            ((LogEventListener)lItr.next()).handleAddLog(lEvent);
        }
    }

    protected synchronized void raiseClearLogEvent() {
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            ((LogEventListener)lItr.next()).handleClearLog();
        }
    }

    public LogManager() {
        FItems = new ArrayList<LogEntry>();
        FEventListeners= new ArrayList();
    }

    public synchronized void addEventListener(LogEventListener aListener)  {
        FEventListeners.add(aListener);
    }

    public synchronized void removeEventListener(LogEventListener aListener)   {
        FEventListeners.remove(aListener);
    }

    public void writeLog(String aText) {
        LogEntry lLogEntry = new LogEntry(aText);
        addLog(lLogEntry);
    }

    public void writeLog(String aText, Date aDate) {
        LogEntry lLogEntry = new LogEntry(aDate, aText);
        addLog(lLogEntry);
    }

    public void ClearLog() {
        FItems.clear();
        raiseClearLogEvent();
    }

}
