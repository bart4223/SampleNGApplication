package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import Uniwork.Visuals.NGStageController;
import javafx.stage.Stage;

public abstract class NGCustomStageItem extends NGComponent implements NGLogEventListener {

    protected String FName;
    protected NGLogManager FLogManager;
    protected Stage FStage;
    protected NGStageManager FStageManager;
    protected NGStageController FStageController = null;
    protected String FCaption = "";
    protected NGPoint2D FPosition;

    protected void CreateStage() {

    }

    protected void LoadStage() {

    }

    protected void ShowStage() {
        FStage.setX(FPosition.getX());
        FStage.setY(FPosition.getY());
        FStage.show();
    }

    protected void InitializeStageController() {
        FStageController.Initialize();
    }

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        if (FStage == null) {
            CreateStage();
        }
        LoadStage();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        InitializeStageController();
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        ShowStage();
    }

    public NGCustomStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager);
        FStageManager = aStageManager;
        FName = aName;
        FStage = aStage;
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        FPosition = new NGPoint2D(0,0);
    }

    public String getName() {
        return FName;
    }

    public NGStageManager getStageManager() {
        return FStageManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        if (NGApplication.Application.getConsoleShowLog()) {
            System.out.println(e.LogEntry.GetFullAsString(NGApplication.Application.getConsoleShowLogEntrySource()));
        }
    }

    @Override
    public void handleClearLog() {

    }

    public void setCaption(String aCaption) {
        FCaption = aCaption;
    }

    public String getCaption() {
        return FCaption;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

}
