package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogManager;
import javafx.stage.Stage;

public class NGUIConsoleStageItem extends NGCustomStageItem {

    @Override
    protected void setContextToController(Object aContext) {
        if (aContext instanceof NGLogManager) {
            NGLogManager lm = (NGLogManager)aContext;
            NGUIConsoleStageController sc = (NGUIConsoleStageController)FStageController;
            sc.addLog(lm.getCompleteLog(false, true));
        }
    }

    public NGUIConsoleStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "NGUIConsoleStage.fxml";
        FWidth = 1000;
        FHeight = 100;
        FCaption = "Console";
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        NGUIConsoleStageController sc = (NGUIConsoleStageController)FStageController;
        sc.addLog(e.LogEntry.GetFullAsString());
    }

    @Override
    public void handleClearLog() {
        NGUIConsoleStageController sc = (NGUIConsoleStageController)FStageController;
        sc.clearLog();
    }

}