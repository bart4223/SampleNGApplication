package Uniwork.Misc;

import Uniwork.Base.NGObject;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGTickGenerator extends NGObject {

    protected Timer FTimer;
    protected CopyOnWriteArrayList<NGTickItem> FItems;
    protected Integer FBaseInterval;
    protected NGLogManager FLogManager;
    protected Boolean FEnabled;

    protected synchronized void DoTick() {
        if (FEnabled) {
            Iterator lItr = FItems.iterator();
            while (lItr.hasNext()) {
                ((NGTickItem) lItr.next()).Tick();
            }
        }
    }

    protected NGTickItem GetItem(String aName) {
        NGTickItem lResult = null;
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            lResult = (NGTickItem)lItr.next();
            if (lResult.getName().equals(aName))
                break;
            else
                lResult = null;
        }
        return lResult;
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    public NGTickGenerator() {
        super();
        FItems = new CopyOnWriteArrayList<NGTickItem>();
        FTimer = new Timer();
        FBaseInterval = 10;
        FLogManager = null;
        FEnabled = true;
    }

    public NGTickGenerator(Integer aBaseInterval) {
        FItems = new CopyOnWriteArrayList<NGTickItem>();
        FTimer = new Timer();
        FBaseInterval = aBaseInterval;
        FEnabled = true;
    }

    public void Initialize() {
        TimerTask lTimerTask = new TimerTask() {
            public void run() {
                synchronized (this) {
                    DoTick();
                }
            }
        };
        FTimer.schedule(lTimerTask, 100, FBaseInterval);
        writeLog("TickGenerator initialized!");
    }

    public void Finalize() {
        FTimer.cancel();
        writeLog("TickGenerator finalized!");
        FTimer = null;
    }

    public void NewItem(String aName, Integer aInterval) {
        NGTickItem lTickItem = new NGTickItem(aName, aInterval);
        FItems.add(lTickItem);
    }

    public void DeleteItem(String aName) {
        NGTickItem lTickitem = GetItem(aName);
        FItems.remove(lTickitem);
    }

    public void SetItemEnabled(String aName, Boolean aValue) {
        SetItemEnabled(aName, aValue, 0);
    }

    public void SetItemEnabled(String aName, Boolean aValue, Integer aDelay) {
        NGTickItem lTickItem = GetItem(aName);
        lTickItem.setEnabled(aValue, aDelay);
    }

    public Boolean GetItemEnabled(String aName) {
        NGTickItem lTickItem = GetItem(aName);
        return lTickItem.getEnabled();
    }

    public void SetItemInterval(String aName, Integer aValue) {
        NGTickItem lTickItem = GetItem(aName);
        lTickItem.setInterval(aValue);
    }

    public Integer GetItemInterval(String aName) {
        NGTickItem lTickItem = GetItem(aName);
        return lTickItem.getInterval();
    }

    public void setItemProp(String aName, String aPropName, Object aValue) {
        NGTickItem lTickItem = GetItem(aName);
        lTickItem.setProp(aPropName, aValue);
    }

    public Object getItemProp(String aName, String aPropName) {
        NGTickItem lTickItem = GetItem(aName);
        return lTickItem.getProp(aPropName);
    }

    public synchronized void addListener(String aName, NGTickListener aListener)  {
        NGTickItem lTickItem = GetItem(aName);
        lTickItem.addTickListener(aListener);
    }

    public synchronized void removeListener(String aName, NGTickListener aListener)   {
        NGTickItem lTickItem = GetItem(aName);
        lTickItem.addTickListener(aListener);
    }

    public void SetAllEnabled(Boolean aValue) {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            NGTickItem lItem = (NGTickItem)lItr.next();
            lItem.setEnabled(aValue);
        }
        if (aValue) {
            writeLog("All TickGenerator items running...");
        }
        else {
            writeLog("All TickGenerator items stopped!");
        }
    }

    public void ToggleAllEnabled( ) {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            NGTickItem lItem = (NGTickItem)lItr.next();
            lItem.setEnabled(!lItem.getEnabled());
        }
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void setEnabled(Boolean aValue) {
        FEnabled = aValue;
        if (aValue) {
            writeLog("TickGenerator running...");
        }
        else {
            writeLog("TickGenerator stopped!");
        }
    }

    public Boolean getEnabled() {
        return FEnabled;
    }

    public void ToggleEnabled() {
        setEnabled(!getEnabled());
    }

}


