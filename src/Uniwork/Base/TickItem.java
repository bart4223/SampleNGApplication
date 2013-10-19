package Uniwork.Base;

import java.util.*;

public class TickItem {

    protected Integer FTicks;
    protected Integer FInterval;
    protected String FName;
    protected Boolean FEnabled;
    protected List FEventListeners;

    protected void DoTick() {
        FTicks = FTicks + 1;
        if (FTicks > FInterval) {
            RaiseTickEvent();
            FTicks = 0;
        }
    }

    protected synchronized void RaiseTickEvent() {
        TickEvent lEvent = new TickEvent(this);
        lEvent.Name = FName;
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            ((TickListener)lItr.next()).handleTick(lEvent);
        }
    }

    public TickItem(String aName, Integer aInterval) {
        FEventListeners= new ArrayList();
        FName = aName;
        FTicks = 0;
        FInterval = aInterval;
        FEnabled = false;
    }

    public void Tick() {
        if (FEnabled) {
            DoTick();
        }
    }

    public String getName() {
        return FName;
    }

    public void setEnabled(Boolean aValue) {
        FEnabled = aValue;
        FTicks = 0;
    }

    public Boolean getEnabled() {
        return FEnabled;
    }

    public void setInterval(Integer aValue) {
        FInterval = aValue;
        FTicks = 0;
    }

    public Integer getInterval() {
        return FInterval;
    }

    public synchronized void addTickListener(TickListener aListener)  {
        FEventListeners.add(aListener);
    }

    public synchronized void removeTickListener(TickListener aListener)   {
        FEventListeners.remove(aListener);
    }

}
