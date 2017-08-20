package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGConsoleScriptFunctions;
import Uniwork.UI.NGUIConsoleStageContext;
import Uniwork.UI.NGUIConsoleStageItem;

public class NGConsoleApplicationModule extends NGVisualApplicationModule {

    protected Boolean FLogDescending;
    protected Boolean FShowCommandArea;
    protected NGConsoleScriptFunctions FConsoleScriptFunctions;

    @Override
    protected Boolean LoadConfiguration() {
        Boolean res = super.LoadConfiguration();
        FLogDescending = Boolean.valueOf(getConfigurationProperty("LogDescending", "false"));
        FShowCommandArea = Boolean.valueOf(getConfigurationProperty("ShowCommandArea", "false"));
        return res;
    }

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGUIConsoleStageItem item = (NGUIConsoleStageItem)FStageManager.addStageItem("Console", FPrimaryStage);
        item.setCaption(getDescription());
        item.setWidth(getWidth());
        item.setHeight(getHeight());
        item.setPosition(getPosX(), getPosY());
        item.setContext(new NGUIConsoleStageContext(NGApplication.Application.getLogManager(), FLogDescending, FShowCommandArea));
        NGApplication.Application.getLogManager().addEventListener(item);
        FConsoleScriptFunctions = new NGConsoleScriptFunctions(NGApplication.Application, item.getConsoleManager());
        FConsoleScriptFunctions.setLogManager(FLogManager);
        if (Definition != null) {
            for (NGConsoleDefinitionCommandItem command : Definition.Commands) {
                item.getConsoleManager().pushCommand(command.getCommand());
            }
        }
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        writeInfo(String.format("%s is ready...", getFullName()));
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FConsoleScriptFunctions.Initialize();
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

    public NGConsoleDefinition Definition;
    
}
