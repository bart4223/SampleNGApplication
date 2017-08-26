package Uniwork.Appl;

import Uniwork.Base.*;
import Uniwork.Misc.*;
import Uniwork.Script.NGScriptExecuter;
import Uniwork.Script.NGScriptFunctionsManager;
import Uniwork.Script.NGScriptManager;
import Uniwork.Visuals.NGCommonDialogs;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class NGApplication extends Application implements NGInitializable, NGLogEventListener, NGObjectResolver, NGObjectRequestRegistration, NGObjectRequestInvoker {

    protected String FName = "NGApplication";
    protected String FDescription = "";
    protected Boolean FInitialized;
    protected NGLogManager FLogManager;
    protected Stage FPrimaryStage = null;
    protected NGApplicationModuleManager FModuleManager;
    protected String FConfigurationFilename = "";
    protected String FDefinitionFilename = "";
    protected Properties FConfiguration;
    protected Boolean FConfigLoaded = false;
    protected Boolean FConsoleShowLogEntrySource = false;
    protected Boolean FConsoleShowLog = true;
    protected NGObjectRequestBroker FORB;
    protected String FResourcePath = "";
    protected NGApplicationDefinition FDefinition;
    protected Boolean FTerminateQuestion = true;
    protected NGScriptExecuter FScriptExecuter;
    protected NGScriptFunctionsManager FScriptFunctionManager;
    protected NGScriptManager FScriptManager;

    protected void writeInfo(String aInfo) {
        FLogManager.writeLog(aInfo, NGLogEntry.LogType.Info, toString());
    }

    protected void writeWarning(String aWarning) {
        FLogManager.writeLog(aWarning, NGLogEntry.LogType.Warning, toString());
    }

    protected void writeError(String aError) {
        FLogManager.writeLog(aError, NGLogEntry.LogType.Error, toString());
    }

    @Override
    public void start(Stage stage) throws Exception {
        FPrimaryStage = stage;
        Initialize();
    }

    @Override
    public void stop() throws Exception {
        Finalize();
        writeInfo("Bye, Bye...");
    }

    protected String getConfigurationProperty(String aName) {
        return getConfigurationProperty(aName, "");
    }

    protected String getConfigurationProperty(String aName, String aDefault) {
        if (FConfigLoaded) {
            String res = FConfiguration.getProperty(aName);
            if (res != null)
                return res;
            else {
                res = FConfiguration.getProperty(NGStrings.getLastString(aName, "."));
                if (res != null)
                    return res;
            }
        }
        return aDefault;
    }

    protected Boolean LoadConfiguration() {
        Boolean res = FConfigurationFilename.length() > 0;
        if (res) {
            try {
                InputStream is = new FileInputStream(FConfigurationFilename);
                FConfiguration.load(is);
                FConfigLoaded = true;
                FConsoleShowLogEntrySource = Boolean.valueOf(getConfigurationProperty("ConsoleShowLogEntrySource", "false"));
                FConsoleShowLog = Boolean.valueOf(getConfigurationProperty("ConsoleShowLog", "true"));
                FTerminateQuestion = Boolean.valueOf(getConfigurationProperty("TerminateQuestion", "true"));
                FLogManager.setLogLevel(Integer.parseInt(getConfigurationProperty("Debuglevel", "0")));
                FDefinitionFilename = getConfigurationProperty("DefinitionFilename");
            }
            catch ( Exception e) {
                writeError(String.format("Error at Application.LoadConfiguration: %s", e.getMessage()));
            }
        }
        return res;
    }

    protected void LoadDefinition() {
        Boolean res = FDefinitionFilename.length() > 0;
        if (res) {
            writeInfo(String.format("Load application definition file %s...", FDefinitionFilename));
            NGObjectXMLDeserializerFile loader = new NGObjectXMLDeserializerFile(null, FDefinitionFilename);
            loader.deserializeObject();
            FDefinition = (NGApplicationDefinition)loader.getTarget();
            if (FDefinition.Modules != null) {
                for (NGApplicationModuleItemDefinition item : FDefinition.getModules()) {
                    try {
                        String name = item.getName();
                        if (name.length() == 0)
                            name = String.format("%d", FModuleManager.getModuleCount() + 1);
                        NGCustomApplicationModule module = addModule(getClass().getClassLoader().loadClass(item.getClassName()), false, name);
                        module.setDescription(item.getDescription());
                        if (module.getDescription().length() == 0)
                            module.setDescription(String.format("%d", FModuleManager.getModuleCount()));
                        module.setConfigurationFilename(item.getConfigurationFilename());
                    }
                    catch (Exception e){
                        writeError(String.format("Error: %s", e.getMessage()));
                    }
                }
            }
            if (FDefinition.Scripts != null) {
                for (NGApplicationScriptItemDefinition script : FDefinition.Scripts) {
                    FScriptManager.addScript(script.Name, script.Filename, script.Description);
                }
            }
            writeInfo(String.format("Application definition file %s loaded.", FDefinitionFilename));
        }
    }

    protected void DoBeforeInitialize() {
        LoadConfiguration();
        writeInfo(String.format("Welcome to %s - %s...", FName, FDescription));
        if (FConfigLoaded)
            writeInfo(String.format("Application configuration file %s loaded.", FConfigurationFilename));
        LoadDefinition();
    }

    protected void DoInitialize() {
        FModuleManager.Initialize();
        FScriptExecuter.Initialize();
        FScriptFunctionManager.Initialize();
        FScriptManager.Initialize();
    }

    protected void DoAfterInitialize() {
        registerObjectRequests();
    }

    protected void registerObjectRequests() {

    }

    protected void DoBeforeFinalize() {

    }

    protected void DoFinalize() {
        FScriptManager.Finalize();
        FScriptFunctionManager.Finalize();
        FScriptExecuter.Finalize();
        FModuleManager.Finalize();
    }

    protected void DoAfterFinalize() {

    }

    protected void DoTerminate() {
        Platform.exit();
    }

    public NGApplication() {
        super();
        Application = this;
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        FModuleManager = new NGApplicationModuleManager();
        FModuleManager.setLogManager(FLogManager);
        FScriptFunctionManager = new NGScriptFunctionsManager(this);
        FScriptFunctionManager.setLogManager(FLogManager);
        FScriptExecuter = new NGScriptExecuter();
        FScriptExecuter.setInvoker(this);
        FScriptExecuter.setLogManager(FLogManager);
        FScriptManager = new NGScriptManager();
        FScriptManager.setLogManager(FLogManager);
        FConfiguration = new Properties();
        FORB = new NGObjectRequestBroker(this);
        FORB.setLogManager(FLogManager);
        FResourcePath = "resources/";
        FDefinition = null;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void Initialize() {
        DoBeforeInitialize();
        try {
            DoInitialize();
            FInitialized = true;
        } finally {
            DoAfterInitialize();
        }
    }

    public void Finalize() {
        DoBeforeFinalize();
        try {
            DoFinalize();
            FInitialized = false;
        } finally {
            DoAfterFinalize();
        }
    }

    public String getConfigurationFilename() {
        return FConfigurationFilename;
    }

    public Boolean getConfigurationPropertyAsBoolean(String aName, Boolean aDefault) {
        Boolean res;
        try {
            String prop;
            String name = NGStrings.getFirstString(aName, ".");
            if (name.equals("Application"))
                prop = getConfigurationProperty(aName);
            else {
                NGCustomApplicationModule module = FModuleManager.getModuleByClassname(name);
                prop = module.getConfigurationProperty(NGStrings.getLastString(aName, "."));
            }
            if (prop.length() == 0)
                res = aDefault;
            else
                res = Boolean.parseBoolean(prop);
        }
        catch(Exception e) {
            res = aDefault;
        }
        return res;
    }

    public Integer getConfigurationPropertyAsInteger(String aName, Integer aDefault) {
        Integer res;
        try {
            String prop;
            String name = NGStrings.getFirstString(aName, ".");
            if (name.equals("Application")) {
                prop = getConfigurationProperty(aName);
            } else {
                NGCustomApplicationModule module = FModuleManager.getModuleByClassname(name);
                prop = module.getConfigurationProperty(NGStrings.getLastString(aName, "."));
            }
            if (prop.length() == 0)
                res = aDefault;
            else
                res = Integer.parseInt(prop);
        }
        catch(Exception e) {
            res = aDefault;
        }
        return res;
    }

    public Boolean getConsoleShowLog() {
        return FConsoleShowLog;
    }

    public Boolean getConsoleShowLogEntrySource() {
        return FConsoleShowLogEntrySource;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        if (FConsoleShowLog)
            System.out.println(e.LogEntry.GetFullAsString(FConsoleShowLogEntrySource));
    }

    @Override
    public void handleClearLog() {

    }

    public void ScriptTerminate() {
        FTerminateQuestion = false;
        Terminate();
    }

    public void Terminate() {
        Boolean terminate = !FTerminateQuestion;
        if (!terminate)
            terminate = NGCommonDialogs.showConfirmDialog(FPrimaryStage, "Quit", String.format("Do you really want to leave %s?", FName)) == NGCommonDialogs.Response.Yes;
        if (terminate)
            DoTerminate();
    }

    public String getName() {
        return FName;
    }

    public String getDescription() {
        return FDescription;
    }

    public static NGApplication Application;

    public NGCustomApplicationModule addModule(String aClassName, String aName) {
        NGCustomApplicationModule res = null;
        try {
            res = addModule(getClass().getClassLoader().loadClass(aClassName), false, aName);
            res.setDescription(aName);
            if (FInitialized) {
                res.Initialize();
            }
        } catch (Exception ex) {
            writeError(ex.getMessage());
        }
        return res;
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, Boolean aInitialize) {
        return addModule(aModuleClass, aInitialize, "");
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, String aName) {
        return addModule(aModuleClass, false, aName);
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, Boolean aInitialize, String aName) {
        NGCustomApplicationModule res = FModuleManager.addModule(aModuleClass, aName);
        if (aInitialize) {
            res.Initialize();
        }
        return res;
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass) {
        return addModule(aModuleClass, false);
    }

    @Override
    public Object ResolveObject(Class aClass) {
        return ResolveObject("", aClass);
    }

    @Override
    public Object ResolveObject(String aName, Class aClass) {
        Object res = null;
        if (aClass.isAssignableFrom(FScriptManager.getClass())) {
            res = FScriptManager;
        } else if (aClass.isAssignableFrom(FORB.getClass())) {
            res = FORB;
        } else if (aClass.isAssignableFrom(getClass())) {
            res = this;
        } else {
            res = FModuleManager.ResolveObject(aName, aClass);
        }
        return res;
    }

    @Override
    public NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        return registerObjectRequest(aName, aObject, aMethod, aObjectMethod, "");
    }

    @Override
    public NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod, String aDescription) {
        NGObjectRequestObject reqobj = FORB.addObject(aName, aObject);
        return reqobj.addMethod(aMethod, aObjectMethod, aDescription);
    }

    @Override
    public void Invoke(NGObjectRequestItem aRequest) {
        FORB.Invoke(aRequest);
    }

    public void Invoke(String aObject, String aMethod) {
        Invoke(new NGObjectRequestItem(aObject, aMethod));
    }

    public String LoadResourceFileContent(String aFilename) {
        return NGMisc.LoadFileContent(NGMisc.combinePath(FResourcePath, aFilename));
    }

    public void ShowStages() {
        Iterator<NGCustomApplicationModule> itr = FModuleManager.getModules();
        while (itr.hasNext()) {
            NGCustomApplicationModule module = itr.next();
            if ( module instanceof NGVisualApplicationModule) {
                NGVisualApplicationModule visualModule = (NGVisualApplicationModule)module;
                visualModule.ShowStages();
            }
        }
    }

    public void HideStages() {
        Iterator<NGCustomApplicationModule> itr = FModuleManager.getModules();
        while (itr.hasNext()) {
            NGCustomApplicationModule module = itr.next();
            if ( module instanceof NGVisualApplicationModule) {
                NGVisualApplicationModule visualModule = (NGVisualApplicationModule)module;
                visualModule.HideStages();
            }
        }
    }

    public void CloseStages() {
        Iterator<NGCustomApplicationModule> itr = FModuleManager.getModules();
        while (itr.hasNext()) {
            NGCustomApplicationModule module = itr.next();
            if ( module instanceof NGVisualApplicationModule) {
                NGVisualApplicationModule visualModule = (NGVisualApplicationModule)module;
                visualModule.CloseStages();
            }
        }
    }

    public void ShowMessage(String aMessage) {
        NGCommonDialogs.showMessageDialog(getName(), aMessage);
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
                        writeInfo(String.format("%s.%s", obj.getName(), method.toString()));
                    } else {
                        writeInfo(String.format("%s.%s : %s", obj.getName(), method.toString(), method.getDescription()));
                    }
                }
            }
        }
    }

    public void RunScript(String aName) {
        String script = FScriptManager.getScript(aName);
        if (script.length() != 0) {
            FScriptExecuter.Execute(script);
        } else {
            writeError(String.format("Script [%s] is empty.", aName));
        }
    }

}
