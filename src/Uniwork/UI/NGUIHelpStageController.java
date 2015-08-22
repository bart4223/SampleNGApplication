package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NGUIHelpStageController extends NGStageController {

    @FXML
    private TextArea Help;

    public void setHelpText(String aText) {
        Help.setText(aText);
    }

    public void clearHelpText() {
        Help.clear();
    }

    public NGUIHelpStageController() {
        this(null);
    }

    public NGUIHelpStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
