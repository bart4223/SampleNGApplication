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
        registerObjectRequest(CApplication,"Quit", "Terminate");
        registerObjectRequest(CApplication,"Exit", "Terminate");
        registerObjectRequest(CApplication,"ShowStages", "ShowStages");
        registerObjectRequest(CApplication,"HideStages", "HideStages");
        registerObjectRequest(CApplication,"Help", "ShowHelp");
        orm = registerObjectRequest(CApplication,"addModule", "addModule");
        orm.addParam("Classname", NGObjectRequestParameter.ParamKind.String);
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
    }

    public NGScriptFunctionsApplication(NGObjectRequestRegistration aORR) {
        super(aORR, NGApplication.Application);
    }

}
