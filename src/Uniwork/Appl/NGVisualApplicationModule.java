package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import javafx.stage.Stage;

public abstract class NGVisualApplicationModule extends NGCustomApplicationModule {

    protected NGStageManager FStageManager;
    protected NGToolboxManager FToolboxManager;
    protected Stage FPrimaryStage;

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

    public NGVisualApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName, aDescription);
        FStageManager = new NGStageManager(this, "StageManager");
        FToolboxManager = new NGToolboxManager(this, "ToolboxManager");
        FPrimaryStage = null;
    }

    public Stage getPrimaryStage() {
        return FPrimaryStage;
    }

    public void setPrimaryStage(Stage aPrimaryStage) {
        FPrimaryStage =  aPrimaryStage;
    }

}
