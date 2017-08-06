package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogManager;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NGUIConsoleStageItem extends NGCustomStageItem {

    @Override
    protected void setContextToController(Object aContext) {
        if (aContext instanceof NGLogManager) {
            NGLogManager lm = (NGLogManager)aContext;
            NGUIConsoleStageController sc = (NGUIConsoleStageController)FStageController;
            sc.addLog(lm.getCompleteLog(false, true));
        }
        else if (aContext instanceof NGUIConsoleStageContext) {
            NGUIConsoleStageContext csc = (NGUIConsoleStageContext)aContext;
            NGUIConsoleStageController sc = (NGUIConsoleStageController)FStageController;
            sc.Descending = csc.Descending;
            sc.setShowCommandArea(csc.ShowCommandArea);
            sc.addLog(csc.LogManager.getCompleteLog(false, csc.Descending));
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
        if (e.LogEntry.GetType() == NGLogEntry.LogType.Error) {
            sc.addLog(e.LogEntry.GetFullAsString(), Color.RED);
        }
        else if (e.LogEntry.GetType() == NGLogEntry.LogType.Warning) {
            sc.addLog(e.LogEntry.GetFullAsString(), Color.DARKORANGE);
        }  else {
            sc.addLog(e.LogEntry.GetFullAsString());
        }
    }

    @Override
    public void handleClearLog() {
        NGUIConsoleStageController sc = (NGUIConsoleStageController)FStageController;
        sc.clearLog();
    }

}