package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

public class NGUIHelpToolboxItem extends NGCustomStageItem {

    @Override
    protected void setContextToController(Object aContext) {
        super.setContextToController(aContext);
        if (aContext instanceof NGUIHelpToolboxContext) {
            NGUIHelpToolboxController sc = (NGUIHelpToolboxController)FStageController;
            sc.setHelpText(((NGUIHelpToolboxContext) aContext).getText());
        }
    }

    public NGUIHelpToolboxItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "NGUIHelpToolbox.fxml";
        FWidth = 800;
        FHeight = 800;
        FCaption = "Help";
        FPosition.setX(100);
        FPosition.setY(100);
        FUnique = true;
    }

}