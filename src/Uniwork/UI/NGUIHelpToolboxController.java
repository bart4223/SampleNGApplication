package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NGUIHelpToolboxController extends NGStageController {

    @FXML
    private TextArea Help;

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
