package Uniwork.Script;

import Uniwork.Appl.NGApplication;
import Uniwork.Base.*;

import java.util.Iterator;

public class NGScriptFunctionsApplication extends NGCustomScriptFunctions {

    public static String CApplication = "Application";

    @Override
    protected void DoRegisterObjectRequests() {
        super.DoRegisterObjectRequests();
        NGObjectRequestMethod orm;
        registerObjectRequest(this, "Quit", "Terminate", "Leave the application.");
        registerObjectRequest(this, "Exit", "Terminate", "Leave the application.");
        registerObjectRequest("ShowStages", "ShowStages", "Show all stages of application.");
        registerObjectRequest("HideStages", "HideStages", "Hide all stages of application.");
        registerObjectRequest("CloseStages", "CloseStages", "Close all stages of application.");
        orm = registerObjectRequest("ShowStage", "ShowStage", "Show all stages of application module.");
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("HideStage", "HideStage", "Hide all stages of application module.");
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("CloseStage", "CloseStage", "Close all stages of application module.");
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest(this, "Help", "ShowHelp", "Show the help for application.");
        orm.addParam("Domain", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest(this, "?", "ShowHelp", "Show the help for domain.");
        orm.addParam("Domain", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("addModule", "addModule", "Add a module to application.");
        orm.addParam("Classname", NGObjectRequestParameter.ParamKind.String);
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("ShowMessage", "ShowMessage", "Shows a message box.");
        orm.addParam("Message", NGObjectRequestParameter.ParamKind.String);
        registerObjectRequest(this, "ListScripts", "ListScripts", "List all scripts.");
        orm = registerObjectRequest(this, "ListScript", "ListScript", "List a script.");
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        registerObjectRequest("ReloadScripts", "ReloadScripts", "Reload all scripts.");
        orm = registerObjectRequest("ReloadScript", "ReloadScript", "Reload a script.");
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest("RunScript", "RunScript", "Run a script.");
        orm.addParam("Name", NGObjectRequestParameter.ParamKind.String);
    }

    @Override
    protected void DoRegisterObjectAliases() {
        super.DoRegisterObjectAliases();
        registerObjectAlias("App");
    }

    public NGScriptFunctionsApplication(NGObjectRequestRegistration aORR) {
        super(aORR, NGApplication.Application);
        FDomain = CApplication;
    }

    public void ShowHelp(String aDomain) {
        NGObjectRequestBroker orb = (NGObjectRequestBroker) NGApplication.Application.ResolveObject(NGObjectRequestBroker.class);
        Iterator<NGObjectRequestObject> objects = orb.getObjects();
        while (objects.hasNext()) {
            NGObjectRequestObject obj = objects.next();
            if (aDomain.length() == 0 || obj.getName().toUpperCase().equals(aDomain.toUpperCase())) {
                Iterator<NGObjectRequestMethod> methods = obj.getMethods();
                while (methods.hasNext()) {
                    NGObjectRequestMethod method = methods.next();
                    if (method.getDescription().length() == 0) {
                        if (obj.HasAlias()) {
                            writeInfo(String.format("%s(%s).%s", obj.getName(), obj.getAlias(), method.toString()));
                        } else {
                            writeInfo(String.format("%s.%s", obj.getName(), method.toString()));
                        }
                    } else {
                        writeInfo(String.format("%s.%s : \"%s\"", obj.getName(), method.toString(), method.getDescription()));
                    }
                }
            }
        }
    }

    public void Terminate() {
        NGApplication.Application.SetTerminateQuestion(false);
        NGApplication.Application.Terminate();
    }

    public void ListScripts() {
        NGScriptManager sm = (NGScriptManager) NGApplication.Application.ResolveObject(NGScriptManager.class);
        Iterator<NGScriptItem> itr = sm.getScripts();
        while (itr.hasNext()) {
            NGScriptItem item = itr.next();
            writeInfo(String.format("%s : %s", item.getName(), item.getFileName()));
        }
    }

    public void ListScript(String aName) {
        NGScriptManager sm = (NGScriptManager) NGApplication.Application.ResolveObject(NGScriptManager.class);
        String script = sm.getScript(aName);
        String[] lines = script.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            writeInfo(lines[i]);
        }
    }

}
