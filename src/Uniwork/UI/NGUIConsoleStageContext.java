package Uniwork.UI;

import Uniwork.Base.NGObject;
import Uniwork.Misc.NGLogManager;

public class NGUIConsoleStageContext extends NGObject {

    public NGUIConsoleStageContext() {
        this(null, true, false);
    }

    public NGUIConsoleStageContext(NGLogManager aNGLogManager) {
        this(aNGLogManager, false, false);
    }

    public NGUIConsoleStageContext(NGLogManager aNGLogManager, Boolean aDescending, Boolean aShowCommandArea) {
        super();
        LogManager = aNGLogManager;
        Descending = aDescending;
        ShowCommandArea = aShowCommandArea;
    }

    public NGLogManager LogManager  = null;
    public Boolean Descending = true;
    public Boolean ShowCommandArea = false;

}
