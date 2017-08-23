package Uniwork.Misc;

import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.Base.NGObjectRequestRegistration;
import Uniwork.Script.NGCustomScriptFunctions;

public class NGConsoleScriptFunctions extends NGCustomScriptFunctions {

    public static String CConsole = "Console";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGObjectRequestMethod orm;
        registerObjectRequest("EchoOn", "EchoOn", "\"Switch the console echo on.\"");
        registerObjectRequest("EchoOff", "EchoOff", "\"Switch the console echo off.\"");
        registerObjectRequest("ClearVariables", "ClearVariables", "\"Clear the variables.\"");
        registerObjectRequest("ShowVariables", "ShowVariables", "\"Show the variables.\"");
        orm = registerObjectRequest("ShowVariable", "ShowVariable", "\"Show a concrete variable.\"");
        orm.addParam("Variable", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest( "RunScript", "RunScript","\"Runs an application script.\"");
        orm.addParam("Script", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest( "ShowScript", "ShowScript", "\"Shows an application script.\"");
        orm.addParam("Script", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest( "Info", "Information", "\"Writes a information to console.\"");
        orm.addParam("Information", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest( "Warning", "Warning", "\"Writes a warning to console.\"");
        orm.addParam("Warning", NGObjectRequestParameter.ParamKind.String);
        orm = registerObjectRequest( "Error", "Error", "\"Writes a error to console.\"");
        orm.addParam("Error", NGObjectRequestParameter.ParamKind.String);
    }

    public NGConsoleScriptFunctions(NGObjectRequestRegistration aORR, Object aDelegate) {
        super(aORR, aDelegate);
        FDomain = CConsole;
    }

}
