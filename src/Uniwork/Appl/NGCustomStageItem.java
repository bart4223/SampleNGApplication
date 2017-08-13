package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGStrings;
import Uniwork.Visuals.NGCustomStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class NGCustomStageItem extends NGComponent implements NGLogEventListener {

    public enum NGDialogResult { None, OK, Cancel, Yes, No }

    protected String FName;
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
    protected Boolean FUnique;
    protected Boolean FShowAfterInitialize = true;
    protected Boolean FOnlyCaption = false;
    protected Boolean FIsDialog = false;
    protected Boolean FIsPrimary = false;
    protected Boolean FContextInitialized = false;
    protected NGDialogResult FDialogResult = NGDialogResult.None;

    protected void CreateStage() {
        FStage = new Stage();
    }

    protected String getStageTitle() {
        String res = getCaption();
        if (!FOnlyCaption && NGApplication.Application != null)
            res = NGStrings.addString(NGApplication.Application.getName(), res, ".");
        return res;
    }

    protected void LoadStage() {
        FXMLLoader lXMLLoader;
        lXMLLoader = new FXMLLoader(getClass().getResource(FFXMLName));
        try {
            lXMLLoader.load();
            FStageController = lXMLLoader.getController();
            FStageController.setStageItem(this);
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle(getStageTitle());
            Scene Scene = new Scene(lRoot, FWidth, FHeight, FColor);
            FStage.setScene(Scene);
            FStage.setResizable(FResizable);
            if (!IsDialog()) {
                if (!FIsPrimary) {
                    FStage.initModality(Modality.NONE);
                }
            }
            else {
                FStage.initModality(Modality.APPLICATION_MODAL);
            }
        }
        catch(Exception e) {
            writeError(e.getMessage());
        }
    }

    protected void ShowStage() {
        FStage.setX(FPosition.getX());
        FStage.setY(FPosition.getY());
        FStage.setTitle(getStageTitle());
        if (!IsDialog()) {
            FStage.show();
        }
        else {
            FStage.showAndWait();
        }
    }

    protected void DoCloseStage() {
        FStage.close();
    }

    protected void DoHideStage() {
        FStage.hide();
    }

    protected void setContextToController(Object aContext) {

    }

    protected void BeforeRenderStage() {
        if (FContext != null && !FContextInitialized) {
            setContextToController(FContext);
            FContextInitialized = true;
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

    protected void DoHide() {
        DoHideStage();
    }

    protected void DoClose() {
        DoCloseStage();
    }

    protected void InitializeStageController() {
        FStageController.Initialize();
    }

    protected void DoReset() {

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
        if (FShowAfterInitialize)
            Show();
    }

    protected void DoSetContext(Object aContext) {
        FContext = aContext;
    }

    public NGCustomStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager);
        FStageManager = aStageManager;
        FName = aName;
        FStage = aStage;
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        FLogManager.addEventListener(NGApplication.Application);
        FPosition = new NGPoint2D(0,0);
        FContext = null;
        FUnique = false;
        FShowAfterInitialize = true;
        FOnlyCaption = false;
        FIsDialog = false;
        FIsPrimary = false;
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

    public void setWidth(Integer aWidth) {
        FWidth = aWidth;
    }

    public Integer getWidth() {
        return FWidth;
    }

    public void setHeight(Integer aHeight) {
        FHeight = aHeight;
    }

    public Integer getHeight() {
        return FHeight;
    }

    public void Show() {
        DoShow();
    }

    public NGDialogResult ShowModal() {
        FDialogResult = NGDialogResult.None;
        Show();
        return getDialogResult();
    }

    public void Hide() {
        DoHide();
    }

    public void Close() {
        DoClose();
    }

    public void setContext(Object aContext) {
        DoSetContext(aContext);
    }

    public Object getContext() {
        return FContext;
    }

    public Boolean IsStageShowing() {
        return FStage.isShowing();
    }

    public Boolean getUnique() {
        return FUnique;
    }

    public void ToggleShow() {
        if (IsStageShowing())
            Hide();
        else
            Show();
    }

    public void Reset() {
        DoReset();
        Invalidate();
    }

    public void Invalidate() {
        RenderStage();
    }

    public Boolean IsDialog() {
        return FIsDialog;
    }

    public NGDialogResult getDialogResult() {
        return FDialogResult;
    }

    public void DialogOK() {
        FDialogResult = NGDialogResult.OK;
        Close();
    }

    public void DialogCancel() {
        FDialogResult = NGDialogResult.Cancel;
        Close();
    }

    public Boolean IsPrimary() {
        return FIsPrimary;
    }

}
