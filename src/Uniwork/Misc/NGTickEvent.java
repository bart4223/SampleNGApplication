package Uniwork.Misc;

import Uniwork.Base.NGPropertyList;

public class NGTickEvent extends java.util.EventObject {

    public String Name;
    public NGPropertyList Props;

    public NGTickEvent(Object source) {
        super(source);
    }

}
