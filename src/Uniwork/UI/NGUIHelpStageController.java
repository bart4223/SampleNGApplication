package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Misc.NGStrings;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NGUIHelpStageController extends NGStageController {

    @FXML
    private TextArea Help;

    public void addHelp(String aText) {
        Help.setText(NGStrings.addString(aText, Help.getText(), "\n"));
    }

    public void clearHelp() {
        Help.clear();
    }

    public NGUIHelpStageController() {
        this(null);
    }

    public NGUIHelpStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
