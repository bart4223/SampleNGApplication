package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGStageController;
import Uniwork.Visuals.NGToolboxController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NGUIHelpToolboxController extends NGToolboxController {

    @FXML
    private TextArea Help;

    @Override
    protected void UpdateControlSize() {
        super.UpdateControlSize();
        Help.setPrefWidth(FStageItem.getWidth());
        Help.setPrefHeight(FStageItem.getHeight());
    }

    public void setHelpText(String aText) {
        Help.setText(aText);
    }

    public void clearHelpText() {
        Help.clear();
    }

    public NGUIHelpToolboxController() {
        this(null);
    }

    public NGUIHelpToolboxController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
