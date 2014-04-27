package Uniwork.Base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NGLogManager {

    protected ArrayList<NGLogEntry> FItems;
    protected List FEventListeners;

    protected void addLog(NGLogEntry aLogEntry) {
        FItems.add(aLogEntry);
        raiseAddLogEvent(aLogEntry);
    }

    protected synchronized void raiseAddLogEvent(NGLogEntry aLogEntry) {
        NGLogEvent lEvent = new NGLogEvent(this, aLogEntry);
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            ((NGLogEventListener)lItr.next()).handleAddLog(lEvent);
        }
    }

    protected synchronized void raiseClearLogEvent() {
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            ((NGLogEventListener)lItr.next()).handleClearLog();
        }
    }

    public NGLogManager() {
        FItems = new ArrayList<NGLogEntry>();
        FEventListeners= new ArrayList();
    }

    public synchronized void addEventListener(NGLogEventListener aListener)  {
        FEventListeners.add(aListener);
    }

    public synchronized void removeEventListener(NGLogEventListener aListener)   {
        FEventListeners.remove(aListener);
    }

    public void writeLog(String aText) {
        NGLogEntry lLogEntry = new NGLogEntry(aText);
        addLog(lLogEntry);
    }

    public void writeLog(String aText, Date aDate) {
        NGLogEntry lLogEntry = new NGLogEntry(aDate, aText);
        addLog(lLogEntry);
    }

    public void clearLog() {
        FItems.clear();
        raiseClearLogEvent();
    }

}
