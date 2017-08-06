package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Misc.NGStrings;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NGUIConsoleStageController extends NGStageController {

    @FXML
    private TextArea Log;

    @Override
    protected void UpdateControlSize() {
        super.UpdateControlSize();
        Log.setPrefWidth(FStageItem.getWidth());
        Log.setPrefHeight(FStageItem.getHeight());
    }

    protected void ScrollDown() {
        Log.selectPositionCaret(Log.getLength());
        Log.deselect();
        Log.setScrollTop(Double.MAX_VALUE);
    }

    public void addLog(String aLogText) {
        if (Descending) {
            Log.setText(NGStrings.addString(aLogText, Log.getText(), "\n"));
        }
        else {
            Log.setText(NGStrings.addString(Log.getText(), aLogText, "\n"));
            ScrollDown();
        }
    }

    public void clearLog() {
        Log.clear();
    }

    public NGUIConsoleStageController() {
        this(null);
    }

    public NGUIConsoleStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

    public Boolean Descending = true;

}
