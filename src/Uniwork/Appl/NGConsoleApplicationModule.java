package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.UI.NGUIConsoleStageContext;

public class NGConsoleApplicationModule extends NGVisualApplicationModule {

    protected Boolean FLogDescending;
    
    @Override
    protected Boolean LoadConfiguration() {
        Boolean res = super.LoadConfiguration();
        FLogDescending = Boolean.valueOf(getConfigurationProperty("LogDescending", "false"));
        return res;
    }

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGCustomStageItem item = FStageManager.addStageItem("Console", FPrimaryStage);
        item.setCaption(getDescription());
        item.setPosition(800, 1200);
        item.setContext(new NGUIConsoleStageContext(NGApplication.Application.getLogManager(), FLogDescending));
        NGApplication.Application.getLogManager().addEventListener(item);
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        writeInfo("Console is ready...");
    }

    @Override
    protected void registerObjectRequests() {
        super.registerObjectRequests();
    }

    public NGConsoleApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FDescription = "Console";
        FStageManager.registerItemClass("Console", "Uniwork.UI.NGUIConsoleStageItem");
    }


}
