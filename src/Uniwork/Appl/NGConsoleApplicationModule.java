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
        item.setPosition(getPosX(), getPosY());
        item.setWidth(getWidth());
        item.setHeight(getHeight());
        item.setContext(new NGUIConsoleStageContext(NGApplication.Application.getLogManager(), FLogDescending));
        NGApplication.Application.getLogManager().addEventListener(item);
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        writeInfo(String.format("%s is ready...", getFullName()));
    }

    @Override
    protected void registerObjectRequests() {
        super.registerObjectRequests();
    }

    public NGConsoleApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FName = "Console";
        FDefaultWidth = 1000;
        FDefaultHeight = 100;
        FDefaultPosX = 800;
        FDefaultPosY = 1200;
        FStageManager.registerItemClass("Console", "Uniwork.UI.NGUIConsoleStageItem");
    }


}
