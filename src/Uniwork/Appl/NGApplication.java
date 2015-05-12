package Uniwork.Appl;

import Uniwork.Base.NGInitializable;
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

public class NGApplication extends Application implements NGInitializable, NGLogEventListener {

    protected String FName = "NGApplication";
    protected Boolean FInitialized;
    protected NGLogManager FLogManager;
    protected Stage FPrimaryStage = null;
    protected NGStageManager FStageManager;
    protected String FConfigurationFilename = "";
    protected Properties FConfiguration;
    protected Boolean FConfigLoaded = false;
    protected Boolean FConsoleShowLogEntrySource = false;
    protected Boolean FConsoleShowLog = true;

    public NGApplication() {
        super();
        Application = this;
        FStageManager = new NGStageManager();
        FLogManager = new NGLogManager();
        FStageManager.setLogManager(FLogManager);
        FLogManager.addEventListener(this);
        FConfiguration = new Properties();
    }

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
        writeInfo(String.format("Welcome to %s...", FName));
    }

    protected String getConfigrationProperty(String aName) {
        if (FConfigLoaded) {
            return FConfiguration.getProperty(aName);
        }
        return "";
    }

    protected void DoBeforeInitialize() {
        LoadConfiguration();
    }

    protected void DoInitialize() {
        FStageManager.Initialize();
    }

    protected void DoAfterInitialize() {

    }

    protected void DoBeforeFinalize() {

    }

    protected void DoFinalize() {
        FStageManager.Finalize();
    }

    protected void DoAfterFinalize() {

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

    public static NGApplication Application;

}
