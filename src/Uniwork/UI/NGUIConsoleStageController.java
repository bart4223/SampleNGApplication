package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Graphics.NGGraphicMisc;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class NGUIConsoleStageController extends NGStageController {

    @FXML
    private TextFlow Log;

    @FXML
    private TextArea Console;

    @FXML
    private ScrollPane ScrollLog;

    @FXML
    private VBox Container;

    @FXML
    protected void handletbCommand(KeyEvent keyEvent){
        switch (keyEvent.getCode()) {
            case ENTER:
                FCommandIndex = ((NGUIConsoleStageItem)FStageItem).ExecuteCommand(Console.getText());
                Console.clear();
                keyEvent.consume();
                break;
            case UP:
                if (FCommandIndex >= 0) {
                    String cmd = ((NGUIConsoleStageItem) FStageItem).getCommand(FCommandIndex);
                    if (FCommandIndex > 0) {
                        FCommandIndex--;
                    }
                    Console.setText(cmd);
                }
                keyEvent.consume();
                break;
            case DOWN:
                if (FCommandIndex >=0 && FCommandIndex < ((NGUIConsoleStageItem) FStageItem).getCommandCount()) {
                    String cmd = ((NGUIConsoleStageItem) FStageItem).getCommand(FCommandIndex);
                    if (FCommandIndex < ((NGUIConsoleStageItem) FStageItem).getCommandCount() - 1) {
                        FCommandIndex++;
                    }
                    Console.setText(cmd);
                }
                keyEvent.consume();
                break;
        }
    }

    protected Boolean FShowCommandArea = false;
    protected Integer FCommandIndex = -1;

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        FCommandIndex = ((NGUIConsoleStageItem) FStageItem).getCommandCount() - 1;
    }

    @Override
    protected void UpdateControlSize() {
        super.UpdateControlSize();
        Container.setPrefWidth(FStageItem.getWidth());
        Container.setPrefHeight(FStageItem.getHeight());
        if (FShowCommandArea) {
            if (!Container.getChildren().contains(Console)) {
                Container.getChildren().add(Console);
            }
            Console.setPrefWidth(FStageItem.getWidth());
            Console.setPrefHeight(FStageItem.getHeight() * 0.25);
        } else {
            Container.getChildren().remove(Console);
        }
        ScrollLog.setPrefWidth(FStageItem.getWidth());
        if (FShowCommandArea) {
            ScrollLog.setPrefHeight(FStageItem.getHeight() * 0.75);
        } else {
            ScrollLog.setPrefHeight(FStageItem.getHeight());
        }
        Log.setPrefWidth(FStageItem.getWidth());
        ScrollLog.setFitToHeight(true);
        Log.setPrefHeight(ScrollLog.getPrefHeight());
    }

    protected void ScrollDown() {
        ScrollLog.layout();
        ScrollLog.setVvalue(1);
        ScrollLog.layout();
    }

    public void addLog(String aLogText) {
        addLog(aLogText, Color.BLACK);
    }

    public void addLog(String aLogText, Color aColor) {
        Text t;
        if (Log.getChildren().size() == 0) {
            t = new Text(aLogText);
        }  else {
            if (Descending) {
                t = new Text(String.format("%s\n", aLogText));
            } else {
                t = new Text(String.format("\n%s", aLogText));
            }
        }
        t.setStyle(String.format("-fx-fill: %s;", NGGraphicMisc.colorToWeb(aColor)));
        if (Descending) {
            Log.getChildren().add(0, t);
        }
        else {
            Log.getChildren().add(t);
            ScrollDown();
        }
    }

    public void clearLog() {
        Log.getChildren().removeAll();
    }

    public NGUIConsoleStageController() {
        this(null);
    }

    public NGUIConsoleStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

    public Boolean Descending = true;

    public void setShowCommandArea(Boolean aShowCommandArea) {
        FShowCommandArea = aShowCommandArea;
        UpdateControlSize();
    }

}
