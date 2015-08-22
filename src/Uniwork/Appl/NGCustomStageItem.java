package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import Uniwork.Visuals.NGCustomStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class NGCustomStageItem extends NGComponent implements NGLogEventListener {

    protected String FName;
    protected NGLogManager FLogManager;
    protected Stage FStage;
    protected NGStageManager FStageManager;
    protected NGCustomStageController FStageController = null;
    protected String FCaption = "";
    protected NGPoint2D FPosition;
    protected Integer FWidth = 0;
    protected Integer FHeight = 0;
    protected String FFXMLName = "";
    protected Boolean FResizable = false;
    protected Color FColor = Color.WHITE;
    protected Object FContext;

    protected void CreateStage() {
        FStage = new Stage();
    }

    protected void LoadStage() {
        FXMLLoader lXMLLoader;
        lXMLLoader = new FXMLLoader(getClass().getResource(FFXMLName));
        try {
            lXMLLoader.load();
            FStageController = lXMLLoader.getController();
            FStageController.setStageItem(this);
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle(String.format("%s.%s", NGApplication.Application.getName(), getCaption()));
            Scene Scene = new Scene(lRoot, FWidth, FHeight, FColor);
            FStage.setScene(Scene);
            FStage.setResizable(FResizable);
        }
        catch(Exception e) {
            writeError(e.getMessage());
        }
    }

    protected void ShowStage() {
        FStage.setX(FPosition.getX());
        FStage.setY(FPosition.getY());
        FStage.show();
    }

    protected void setContextToController(Object aContext) {

    }

    protected void BeforeRenderStage() {
        if (FContext != null) {
            setContextToController(FContext);
        }
    }

    protected void DoRenderStage() {
        FStageController.RenderScene();
    }

    protected void RenderStage() {
        BeforeRenderStage();
        try {
            DoRenderStage();
        }
        finally {
            AfterRenderStage();
        }
    }

    protected void AfterRenderStage() {

    }

    protected void DoShow() {
        RenderStage();
        ShowStage();
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
        Show();
    }

    public NGCustomStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager);
        FStageManager = aStageManager;
        FName = aName;
        FStage = aStage;
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        FPosition = new NGPoint2D(0,0);
        FContext = null;
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

    public void Show() {
        DoShow();
    }

    public void setContext(Object aContext) {
        FContext = aContext;
    }

    public Object getContext() {
        return FContext;
    }

}
