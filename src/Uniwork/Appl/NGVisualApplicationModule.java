package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import javafx.stage.Stage;

public abstract class NGVisualApplicationModule extends NGCustomApplicationModule {

    protected NGStageManager FStageManager;
    protected Stage FPrimaryStage = null;

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        FStageManager.Initialize();
    }

    @Override
    protected void DoBeforeFinalize() {
        super.DoBeforeFinalize();
        FStageManager.Finalize();
    }

    public NGVisualApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName, aDescription);
        FStageManager = new NGStageManager();
    }

    public Stage getPrimaryStage() {
        return FPrimaryStage;
    }

    public void setPrimaryStage(Stage aPrimaryStage) {
        FPrimaryStage =  aPrimaryStage;
    }

}
