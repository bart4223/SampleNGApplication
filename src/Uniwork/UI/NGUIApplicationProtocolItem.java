package Uniwork.UI;

import Uniwork.Appl.NGApplicationProtocol;
import Uniwork.Appl.NGStageManager;
import Uniwork.Appl.NGToolboxItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NGUIApplicationProtocolItem extends NGToolboxItem {

    protected NGApplicationProtocol FProtocol;

    @Override
    protected void setContextToController(Object aContext) {
        super.setContextToController(aContext);
        if (aContext instanceof NGUIApplicationProtocolContext) {
            NGUIApplicationProtocolController sc = (NGUIApplicationProtocolController)FStageController;
            FProtocol = ((NGUIApplicationProtocolContext)aContext).getApplicationProtocol();
            sc.setProtocol(FProtocol);
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

    public void SaveApplicationProtocolAsText() {
        FileChooser fileChooser = new FileChooser();
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        fileChooser.setInitialDirectory(userDirectory);
        fileChooser.setTitle("Save as TXT");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File chosenFile = fileChooser.showSaveDialog(FStage.getOwner());
        if (chosenFile != null) {
            FProtocol.SaveAsText(chosenFile);
        }
    }

}