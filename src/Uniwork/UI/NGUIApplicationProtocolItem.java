package Uniwork.UI;

import Uniwork.Appl.NGStageManager;
import Uniwork.Appl.NGToolboxItem;
import javafx.stage.Stage;

public class NGUIApplicationProtocolItem extends NGToolboxItem {

    @Override
    protected void setContextToController(Object aContext) {
        super.setContextToController(aContext);
        if (aContext instanceof NGUIApplicationProtocolContext) {
            NGUIApplicationProtocolController sc = (NGUIApplicationProtocolController)FStageController;
            sc.setProtocol(((NGUIApplicationProtocolContext)aContext).getApplicationProtocol());
        }
    }

    public NGUIApplicationProtocolItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "NGUIApplicationProtocol.fxml";
        FWidth = 800;
        FHeight = 800;
        FCaption = "Protocol";
        FPosition.setX(1000);
        FPosition.setY(100);
    }

}