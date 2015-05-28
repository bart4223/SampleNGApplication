package Uniwork.Appl;

import Uniwork.Base.NGComponent;

public abstract class NGVisualApplicationModule extends NGCustomApplicationModule {

    protected NGStageManager FStageManager;

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

}
