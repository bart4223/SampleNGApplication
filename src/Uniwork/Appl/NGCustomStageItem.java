package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import javafx.stage.Stage;

public abstract class NGCustomStageItem extends NGComponent implements NGLogEventListener {

    protected String FName;
    protected NGLogManager FLogManager;
    protected Stage FStage;
    protected NGStageManager FStageManager;

    protected void CreateStage() {

    }

    protected void LoadStage() {

    }

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        if (FStage == null) {
            CreateStage();
        }
        LoadStage();
    }

    public NGCustomStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager);
        FStageManager = aStageManager;
        FName = aName;
        FStage = aStage;
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
    }

    public String getName() {
        return FName;
    }

    public NGStageManager getStageManager() {
        return FStageManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        if (NGApplication.Application.getConsoleShowLog()) {
            System.out.println(e.LogEntry.GetFullAsString(NGApplication.Application.getConsoleShowLogEntrySource()));
        }
    }

    @Override
    public void handleClearLog() {

    }

}
