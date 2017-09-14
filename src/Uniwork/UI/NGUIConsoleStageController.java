package Uniwork.UI;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObjectJSONDeserializer;
import Uniwork.Graphics.NGGraphicMisc;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogObject;
import Uniwork.Visuals.NGStageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
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
                FCommandIndex = ((NGUIConsoleStageItem)FStageItem).ExecuteSingleCommand(Console.getText());
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

    public void addLog(NGLogEntry aLogEntry) {
        try {
            Class cl = getClass().getClassLoader().loadClass(aLogEntry.getJSONClass());
            NGObjectJSONDeserializer deserializer = new NGObjectJSONDeserializer(cl);
            deserializer.setJSON(aLogEntry.getJSON());
            deserializer.deserializeObject();
            Object target = deserializer.getTarget();
            if (target instanceof NGLogObject.ScriptMessage) {
                NGLogObject.ScriptMessage sm = (NGLogObject.ScriptMessage) target;
                addLog(aLogEntry.GetDateAsString() + " " + sm.Message, Color.web(sm.Color),  sm.ScriptCaption, sm.Script);
            } else if (target instanceof NGLogObject.ColorMessage) {
                NGLogObject.ColorMessage cm = (NGLogObject.ColorMessage) target;
                addLog(aLogEntry.GetDateAsString() + " " + cm.Message, Color.web(cm.Color));
            } else if (target instanceof NGLogObject.SimpleMessage) {
                addLog(aLogEntry.GetDateAsString() + " " + ((NGLogObject.SimpleMessage)target).Message);
            }
        } catch (Exception e) {
            addLog(e.getMessage(), Color.RED);
        }
    }

    public void addLog(String aLogText, Color aColor, String aScriptCaption, final String aScript) {
        HBox hbox = new HBox(5);
        Button btn = new Button();
        btn.setText(aScriptCaption);
        btn.setStyle(String.format("-fx-fill: %s;", NGGraphicMisc.colorToWeb(aColor)));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                NGApplication.Application.RunScriptDirect(aScript);
            }
        });
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
        t.setStyle(btn.getStyle());
        hbox.getChildren().add(t);
        hbox.getChildren().add(btn);
        addNewline();
        if (Descending) {
            Log.getChildren().add(0, hbox);
        }
        else {
            Log.getChildren().add(hbox);
            ScrollDown();
        }
    }

    public void addNewline() {
        addLog("");
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
