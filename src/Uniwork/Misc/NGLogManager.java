package Uniwork.Misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NGLogManager {

    protected ArrayList<NGLogEntry> FItems;
    protected List FEventListeners;
    protected int FLogLevel;

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
        FLogLevel = 0;
    }

    public synchronized void addEventListener(NGLogEventListener aListener)  {
        FEventListeners.add(aListener);
    }

    public synchronized void removeEventListener(NGLogEventListener aListener)   {
        FEventListeners.remove(aListener);
    }

    public void writeLog(String aText) {
        writeLog(aText, new Date(), "");
    }

    public void writeLog(String aText, String aSource) {
        writeLog(aText, new Date(), aSource);
    }

    public void writeLog(String aText, Date aDate) {
        writeLog(aText, aDate, "");
    }

    public void writeLog(String aText, Date aDate, String aSource) {
        NGLogEntry lLogEntry = new NGLogEntry(aDate, aText, aSource);
        addLog(lLogEntry);
    }

    public void writeLog(int aLogLevel, String aText) {
        writeLog(aLogLevel, aText, new Date(), "");
    }

    public void writeLog(int aLogLevel, String aText, String aSource) {
        writeLog(aLogLevel, aText, new Date(), aSource);
    }

    public void writeLog(int aLogLevel, String aText, Date aDate) {
        writeLog(aLogLevel, aText, aDate, "");
    }

    public void writeLog(int aLogLevel, String aText, Date aDate, String aSource) {
        if (aLogLevel <= FLogLevel) {
            NGLogEntry lLogEntry = new NGLogEntry(aDate, aText, aSource);
            addLog(lLogEntry);
        }
    }

    public void clearLog() {
        FItems.clear();
        raiseClearLogEvent();
    }

    public void setLogLevel(int aLogLevel) {
        FLogLevel = aLogLevel;
    }

    public int getLogLevel() {
        return FLogLevel;
    }

}
