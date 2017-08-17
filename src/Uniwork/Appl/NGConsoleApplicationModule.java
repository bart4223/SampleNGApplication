package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.Misc.NGMisc;
import Uniwork.UI.NGUIConsoleStageContext;
import Uniwork.UI.NGUIConsoleStageItem;

public class NGConsoleApplicationModule extends NGVisualApplicationModule {

    protected Boolean FLogDescending;
    protected Boolean FShowCommandArea;

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
        NGCustomStageItem item = FStageManager.addStageItem("Console", FPrimaryStage);
        item.setCaption(getDescription());
        item.setWidth(getWidth());
        item.setHeight(getHeight());
        item.setPosition(getPosX(), getPosY());
        item.setContext(new NGUIConsoleStageContext(NGApplication.Application.getLogManager(), FLogDescending, FShowCommandArea));
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
        NGObjectRequestMethod orm = registerObjectRequest(this, "RunScript", "RunScript");
        orm.addParam("Script", NGObjectRequestParameter.ParamKind.String);
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

    public void RunScript(String aScript) {
        String script = NGMisc.LoadFileContent(aScript);
        NGUIConsoleStageItem si = (NGUIConsoleStageItem)FStageManager.getItem("Console");
        si.RunScript(script);
    }

}
