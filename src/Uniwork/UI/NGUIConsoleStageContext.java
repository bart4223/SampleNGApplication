package Uniwork.UI;

import Uniwork.Base.NGObject;
import Uniwork.Misc.NGLogManager;

public class NGUIConsoleStageContext extends NGObject {

    public NGUIConsoleStageContext() {
        this(null, true);
    }

    public NGUIConsoleStageContext(NGLogManager aNGLogManager) {
        this(aNGLogManager, false);
    }

    public NGUIConsoleStageContext(NGLogManager aNGLogManager, Boolean aDescending) {
        super();
        LogManager = aNGLogManager;
        Descending = aDescending;
    }

    public NGLogManager LogManager  = null;
    public Boolean Descending = true;

}
