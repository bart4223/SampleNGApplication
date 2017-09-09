package Sample;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;

public class MainStageController extends NGStageController {

    @FXML
    protected void handleQuit() {
        Invoke("Application", "Quit");
    }

    @FXML
    protected void handleShow() {
        Invoke("Application", "ShowStages");
    }

    public MainStageController() {
        this(null);
    }

    public MainStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}