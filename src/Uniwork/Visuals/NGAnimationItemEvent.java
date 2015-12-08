package Uniwork.Visuals;

import java.util.EventObject;

public class NGAnimationItemEvent extends EventObject {

    public NGAnimationItemEvent(NGCustomAnimationItem source) {
        super(source);
    }

    public NGCustomAnimationItem getSource() {
        return (NGCustomAnimationItem)super.getSource();
    }

}
