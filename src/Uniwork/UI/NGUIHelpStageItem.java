package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGHelpContext;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

public class NGUIHelpStageItem extends NGCustomStageItem {

    @Override
    protected void setContextToController(Object aContext) {
        super.setContextToController(aContext);
        if (aContext instanceof NGHelpContext) {
            NGUIHelpStageController sc = (NGUIHelpStageController)FStageController;
            sc.addHelp(((NGHelpContext)aContext).getText());
        }
    }

    public NGUIHelpStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "NGUIHelpStage.fxml";
        FWidth = 800;
        FHeight = 800;
        FCaption = "Help";
        FPosition.setX(100);
        FPosition.setY(100);
    }

}