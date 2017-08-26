package Uniwork.Script;

import Uniwork.Appl.NGApplication;
import Uniwork.Base.*;

public class NGScriptFunctionsApplication extends NGCustomScriptFunctions {

    public static String CApplication = "Application";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGObjectRequestMethod orm;
        registerObjectRequest("Quit", "ScriptTerminate", "\"Leave the application.\"");
        registerObjectRequest("Exit", "ScriptTerminate", "\"Leave the application.\"");
        registerObjectRequest("ShowStages", "ShowStages","\"Show all stages of application.\"");
        registerObjectRequest("HideStages", "HideStages","\"Hide all stages of application.\"");
        orm = registerObjectRequest("Help", "ShowHelp","\"Show the help of application.\"");
        orm.addParam("Domain", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("?", "ShowHelp", "\"Show the help of application.\"");
        orm.addParam("Domain", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("addModule", "addModule","\"Add a module to application.\"");
        orm.addParam("Classname", NGObjectRequestParameter.ParamKind.String);
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("ShowMessage", "ShowMessage", "\"Shows a message box.\"");
        orm.addParam("Message", NGObjectRequestParameter.ParamKind.String);
    }

    public NGScriptFunctionsApplication(NGObjectRequestRegistration aORR) {
        super(aORR, NGApplication.Application);
        FDomain = CApplication;
    }

}
