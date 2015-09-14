package Uniwork.Appl;

import javafx.stage.Stage;

public class NGToolboxItem extends NGCustomStageItem {

    public NGToolboxItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FShowAfterInitialize = false;
    }

}
