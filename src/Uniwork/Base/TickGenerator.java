package Uniwork.Base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class TickGenerator {

    protected Timer FTimer;
    protected ArrayList<TickItem> FItems;

    protected void DoTick() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((TickItem)lItr.next()).Tick();
        }
    }

    protected TickItem GetItem(String aName) {
        TickItem lResult = null;
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            lResult = (TickItem)lItr.next();
            if (lResult.getName().equals(aName))
                break;
            else
                lResult = null;
        }
        return lResult;
    }

    public TickGenerator() {
        FItems = new ArrayList<TickItem>();
        FTimer = new Timer();
    }

    public void Initialize() {
        TimerTask lTimerTask = new TimerTask() {
            public void run() {
                synchronized (this) {
                    DoTick();
                }
            }
        };
        FTimer.schedule(lTimerTask,100,10);
    }

    public void Finalize() {
        FTimer.cancel();
        FTimer = null;
    }

    public void NewItem(String aName, Integer aInterval) {
        TickItem lTickItem = new TickItem(aName, aInterval);
        FItems.add(lTickItem);
    }

    public void SetItemEnabled(String aName, Boolean aValue) {
        TickItem lTickItem = GetItem(aName);
        lTickItem.setEnabled(aValue);
    }

    public Boolean GetItemEnabled(String aName) {
        TickItem lTickItem = GetItem(aName);
        return lTickItem.getEnabled();
    }

    public void SetItemInterval(String aName, Integer aValue) {
        TickItem lTickItem = GetItem(aName);
        lTickItem.setInterval(aValue);
    }

    public Integer GetItemInterval(String aName) {
        TickItem lTickItem = GetItem(aName);
        return lTickItem.getInterval();
    }

    public synchronized void addListener(String aName, TickListener aListener)  {
        TickItem lTickItem = GetItem(aName);
        lTickItem.addTickListener(aListener);
    }

    public synchronized void removeListener(String aName, TickListener aListener)   {
        TickItem lTickItem = GetItem(aName);
        lTickItem.addTickListener(aListener);
    }

}


