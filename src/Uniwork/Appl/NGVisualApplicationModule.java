package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import javafx.stage.Stage;

public abstract class NGVisualApplicationModule extends NGCustomApplicationModule {

    protected NGStageManager FStageManager;
    protected NGToolboxManager FToolboxManager;
    protected Stage FPrimaryStage;
    protected Integer FPosX;
    protected Integer FPosY;
    protected Integer FDefaultPosX = 0;
    protected Integer FDefaultPosY = 0;
    protected Integer FWidth;
    protected Integer FHeight;
    protected Integer FDefaultWidth = 100;
    protected Integer FDefaultHeight = 100;

    @Override
    protected Boolean LoadConfiguration() {
        Boolean res = super.LoadConfiguration();
        FPosX = Integer.parseInt(getConfigurationProperty("PosX", getDefaultPosX().toString()));
        FPosY = Integer.parseInt(getConfigurationProperty("PosY", getDefaultPosY().toString()));
        FWidth = Integer.parseInt(getConfigurationProperty("Width", getDefaultWidth().toString()));
        FHeight = Integer.parseInt(getConfigurationProperty("Height", getDefaultHeight().toString()));
        return res;
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FStageManager.Initialize();
        FToolboxManager.Initialize();
    }

    @Override
    protected void DoFinalize() {
        FToolboxManager.Finalize();
        FStageManager.Finalize();
        super.DoFinalize();
    }

    public NGVisualApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FStageManager = new NGStageManager(this, String.format("%s.%s", aName, "StageManager"));
        FToolboxManager = new NGToolboxManager(this, String.format("%s.%s", aName, "ToolboxManager"));
        FPrimaryStage = null;
    }

    public Stage getPrimaryStage() {
        return FPrimaryStage;
    }

    public void setPrimaryStage(Stage aPrimaryStage) {
        FPrimaryStage =  aPrimaryStage;
    }

    public void ShowStages() {
        FStageManager.ShowStages();
    }

    public void HideStages() {
        FStageManager.HideStages();
    }

    public void CloseStages() {
        FStageManager.CloseStages();
    }

    public Integer getPosX() {
        return FPosX;
    }

    public Integer getPosY() {
        return FPosY;
    }

    public Integer getDefaultPosX() {
        return FDefaultPosX;
    }

    public Integer getDefaultPosY() {
        return FDefaultPosY;
    }

    public void setWidth(Integer aWidth) {
        FWidth = aWidth;
    }

    public Integer getWidth() {
        return FWidth;
    }

    public Integer getDefaultWidth() {
        return FDefaultWidth;
    }

    public void setHeight(Integer aHeight) {
        FHeight = aHeight;
    }

    public Integer getHeight() {
        return FHeight;
    }

    public Integer getDefaultHeight() {
        return FDefaultHeight;
    }

    public Integer getStageCount() {
        return FStageManager.getItemCount();
    }

}
