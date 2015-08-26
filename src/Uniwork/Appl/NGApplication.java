package Uniwork.Appl;

import Uniwork.Base.*;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class NGApplication extends Application implements NGInitializable, NGLogEventListener, NGObjectResolver, NGObjectRequestRegistration, NGObjectRequestInvoker {

    protected String FName = "NGApplication";
    protected String FDescription = "";
    protected Boolean FInitialized;
    protected NGLogManager FLogManager;
    protected Stage FPrimaryStage = null;
    protected NGApplicationModuleManager FModuleManager;
    protected String FConfigurationFilename = "";
    protected Properties FConfiguration;
    protected Boolean FConfigLoaded = false;
    protected Boolean FConsoleShowLogEntrySource = false;
    protected Boolean FConsoleShowLog = true;
    protected NGObjectRequestBroker FORB;
    protected String FResourcePath = "";

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

    protected void LoadConfiguration() {
        Boolean result = FConfigurationFilename.length() > 0;
        if (result) {
            try {
                InputStream is = new FileInputStream(FConfigurationFilename);
                FConfiguration.load(is);
                FConfigLoaded = true;
                FConsoleShowLogEntrySource = Boolean.valueOf(getConfigrationProperty("ConsoleShowLogEntrySource"));
                FConsoleShowLog = Boolean.valueOf(getConfigrationProperty("ConsoleShowLog"));
                FLogManager.setLogLevel(Integer.parseInt(getConfigrationProperty("Debuglevel")));
            }
            catch ( Exception e) {
                writeError(String.format("Error in LoadConfiguration: %s", e.getMessage()));
            }
        }
    }

    protected String getConfigrationProperty(String aName) {
        if (FConfigLoaded) {
            return FConfiguration.getProperty(aName);
        }
        return "";
    }

    protected void DoBeforeInitialize() {
        LoadConfiguration();
        writeInfo(String.format("Welcome to %s...%s", FName, FDescription));
    }

    protected void DoInitialize() {
        FModuleManager.Initialize();
    }

    protected void DoAfterInitialize() {
        registerObjectRequests();
    }

    protected void registerObjectRequests() {
        registerObjectRequest("Application", this, "Quit", "Terminate");
    }

    protected void DoBeforeFinalize() {

    }

    protected void DoFinalize() {
        FModuleManager.Finalize();
    }

    protected void DoAfterFinalize() {

    }

    public NGApplication() {
        super();
        Application = this;
        FLogManager = new NGLogManager();
        FModuleManager = new NGApplicationModuleManager();
        FModuleManager.setLogManager(FLogManager);
        FLogManager.addEventListener(this);
        FConfiguration = new Properties();
        FORB = new NGObjectRequestBroker(this);
        FORB.setLogManager(FLogManager);
        FResourcePath = "resources/";
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

    public Boolean getConsoleShowLog() {
        return FConsoleShowLog;
    }

    public Boolean getConsoleShowLogEntrySource() {
        return FConsoleShowLogEntrySource;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        if (FConsoleShowLog) {
            System.out.println(e.LogEntry.GetFullAsString(FConsoleShowLogEntrySource));
        }
    }

    @Override
    public void handleClearLog() {

    }

    public void Terminate() {
        Platform.exit();
    }

    public String getName() {
        return FName;
    }

    public String getDescription() {
        return FDescription;
    }

    public static NGApplication Application;

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, Boolean aInitialize) {
        return addModule(aModuleClass, aInitialize, "");
    }

    public NGCustomApplicationModule addModule(Class<?> aModuleClass, Boolean aInitialize, String aDescription) {
        NGCustomApplicationModule res = FModuleManager.addModule(aModuleClass);
        res.setDescription(aDescription);
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
        return null;
    }

    @Override
    public Object ResolveObject(String aName, Class aClass) {
        return null;
    }

    @Override
    public NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        NGObjectRequestObject reqobj = FORB.addObject(aName, aObject);
        return reqobj.addMethod(aMethod, aObjectMethod);
    }

    @Override
    public void Invoke(NGObjectRequestItem aRequest) {
        FORB.Invoke(aRequest);
    }

    public void Invoke(String aObject, String aMethod) {
        Invoke(new NGObjectRequestItem(aObject, aMethod));
    }

    public String LoadResourceFileContent(String aFilename) {
        String lResult = "";
        try {
            InputStream lFileStream = new FileInputStream(FResourcePath + aFilename);
            try {
                if (lFileStream != null) {
                    int lContent;
                    while ((lContent = lFileStream.read()) != -1) {
                        lResult = lResult + (char)lContent;
                    }
                }
            } finally {
                if (lFileStream != null) {
                    lFileStream.close();
                }
            }
        } catch (Exception e) {
        }
        return lResult;
    }

}
