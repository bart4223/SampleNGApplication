package Sample;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGComponent;

public class MainApplicationModule extends NGVisualApplicationModule {

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGCustomStageItem item = FStageManager.addStageItem("Main", FPrimaryStage);
        item.setCaption(getDescription());
        item.setPosition(1000, 100);
    }

    @Override
    protected void registerObjectRequests() {
        super.registerObjectRequests();
    }

    public MainApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FDescription = "Main";
        FStageManager.registerItemClass("Main", "Sample.MainStageItem");
    }

}
