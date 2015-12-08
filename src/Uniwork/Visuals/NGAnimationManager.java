package Uniwork.Visuals;

import Uniwork.Base.NGComponent;

import java.util.ArrayList;
import java.util.Iterator;

public class NGAnimationManager extends NGComponent {

    protected ArrayList<NGCustomAnimationItem> FItems;

    protected void DoPlayItem(NGCustomAnimationItem aItem) {
        aItem.Play();
    }

    protected void DoStopItem(NGCustomAnimationItem aItem) {
        aItem.Stop();
    }

    public NGAnimationManager() {
        this(null);
    }

    public NGAnimationManager(NGComponent aOwner) {
        this(aOwner, "");
    }

    public NGAnimationManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FItems = new ArrayList<NGCustomAnimationItem>();
    }

    public Iterator<NGCustomAnimationItem> getItems() {
        return FItems.iterator();
    }

    public void addItem(NGCustomAnimationItem aItem) {
        addItem(aItem, false);
    }

    public void addItem(NGCustomAnimationItem aItem, Boolean aPlay) {
        FItems.add(aItem);
        if (aPlay)
            aItem.Play();
    }

    public void removeItem(NGCustomAnimationItem aItem) {
        aItem.Stop();
        FItems.remove(aItem);
    }

    public void PlayAll() {
        for (NGCustomAnimationItem item : FItems)
            DoPlayItem(item);
    }

    public void StopAll() {
        for (NGCustomAnimationItem item : FItems)
            DoStopItem(item);
    }

    public Boolean IsPlaying() {
        for (NGCustomAnimationItem item : FItems) {
            if (item.IsPlaying())
                return true;
        }
        return false;
    }

    public void TogglePlayAll() {
        if (IsPlaying())
            StopAll();
        else
            PlayAll();
    }

}
