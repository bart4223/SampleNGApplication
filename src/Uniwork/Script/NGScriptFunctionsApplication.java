package Uniwork.Script;

import Uniwork.Appl.NGApplication;
import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsApplication extends NGCustomScriptFunctions {

    public static String CApplication = "Application";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGObjectRequestMethod orm;
        registerObjectRequest("Quit", "Terminate");
        registerObjectRequest("Exit", "Terminate");
        registerObjectRequest("ShowStages", "ShowStages");
        registerObjectRequest("HideStages", "HideStages");
        registerObjectRequest("Help", "ShowHelp");
        registerObjectRequest("?", "ShowHelp");
        orm = registerObjectRequest("addModule", "addModule");
        orm.addParam("Classname", NGObjectRequestParameter.ParamKind.String);
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
    }

    public NGScriptFunctionsApplication(NGObjectRequestRegistration aORR) {
        super(aORR, NGApplication.Application);
        FDomain = CApplication;
    }

}
