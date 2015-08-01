package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import javafx.stage.Stage;

public abstract class NGVisualApplicationModule extends NGCustomApplicationModule {

    protected NGStageManager FStageManager;
    protected Stage FPrimaryStage;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FStageManager.Initialize();
    }

    @Override
    protected void DoFinalize() {
        FStageManager.Finalize();
        super.DoFinalize();
    }

    public NGVisualApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName, aDescription);
        FStageManager = new NGStageManager(this, "StageManager");
        FPrimaryStage = null;
    }

    public Stage getPrimaryStage() {
        return FPrimaryStage;
    }

    public void setPrimaryStage(Stage aPrimaryStage) {
        FPrimaryStage =  aPrimaryStage;
    }

}
