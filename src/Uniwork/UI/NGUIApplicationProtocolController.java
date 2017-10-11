package Uniwork.UI;

import Uniwork.Appl.NGApplicationProtocol;
import Uniwork.Appl.NGApplicationProtocolItem;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Misc.NGStrings;
import Uniwork.Visuals.NGToolboxController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Iterator;

public class NGUIApplicationProtocolController extends NGToolboxController {

    @FXML
    private TextArea Protocol;

    @Override
    protected void UpdateControlSize() {
        super.UpdateControlSize();
        Protocol.setPrefWidth(FStageItem.getWidth());
        Protocol.setPrefHeight(FStageItem.getHeight());
    }

    public void setProtocol(NGApplicationProtocol aApplicationProtocol) {
        Protocol.clear();
        Iterator<NGApplicationProtocolItem> itr = aApplicationProtocol.getItems();
        while (itr.hasNext()) {
            NGApplicationProtocolItem item = itr.next();
            String str = item.GetText();
            Protocol.setText(NGStrings.addString(Protocol.getText(), str, "\n"));
        }
    }

    public void clearProtocol() {
        Protocol.clear();
    }

    public NGUIApplicationProtocolController() {
        this(null);
    }

    public NGUIApplicationProtocolController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
