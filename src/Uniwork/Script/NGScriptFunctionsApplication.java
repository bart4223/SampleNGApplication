package Uniwork.Script;

import Uniwork.Appl.NGApplication;
import Uniwork.Base.*;

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
        orm = registerObjectRequest("Help", "ShowHelp");
        orm.addParam("Domain", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("?", "ShowHelp");
        orm.addParam("Domain", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("addModule", "addModule");
        orm.addParam("Classname", NGObjectRequestParameter.ParamKind.String);
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("ShowMessage", "ShowMessage");
        orm.addParam("Message", NGObjectRequestParameter.ParamKind.String);
    }

    public NGScriptFunctionsApplication(NGObjectRequestRegistration aORR) {
        super(aORR, NGApplication.Application);
        FDomain = CApplication;
    }

}
