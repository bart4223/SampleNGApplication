package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NGUIConsoleStageController extends NGStageController {

    @FXML
    private TextArea Log;

    @Override
    protected void UpdateSize() {
        super.UpdateSize();
        Log.setPrefWidth(FStageItem.getWidth());
        Log.setPrefHeight(FStageItem.getHeight());
    }

    public void addLog(String aLogText) {
        Log.setText(String.format("%s\n%s", aLogText, Log.getText()));
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

}
