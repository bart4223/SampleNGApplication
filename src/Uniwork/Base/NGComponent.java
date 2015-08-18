package Uniwork.Base;

import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogManager;

public abstract class NGComponent extends NGObject implements NGInitializable {

    protected Boolean FInitialized;
    protected NGLogManager FLogManager;
    protected NGComponent FOwner;
    protected String FName;

    protected void DoBeforeInitialize() {

    }

    protected void DoInitialize() {

    }

    protected void DoAfterInitialize() {

    }

    protected void DoBeforeFinalize() {

    }

    protected void DoFinalize() {

    }

    protected void DoAfterFinalize() {

    }

    protected void writeInfo(String aInfo) {
        writeInfo(0, aInfo);
    }

    protected void writeInfo(Integer aLogLevel, String aInfo) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aInfo, NGLogEntry.LogType.Info, toString());
        }
    }

    protected void writeWarning(String aWarning) {
        if (FLogManager != null) {
            FLogManager.writeLog(aWarning, NGLogEntry.LogType.Warning, toString());
        }
    }

    protected void writeError(String aError) {
        if (FLogManager != null) {
            FLogManager.writeLog(aError, NGLogEntry.LogType.Error, toString());
        }
    }

    public NGComponent() {
        this(null);
    }

    public NGComponent(NGComponent aOwner) {
        this(aOwner, "");
    }

    public NGComponent(NGComponent aOwner, String aName) {
        super();
        FName = aName;
        FOwner = aOwner;
        FLogManager = null;
        FInitialized = false;
    }

    public String getName() {
        return FName;
    }

    public NGComponent getOwner() {
        return FOwner;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager =  aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    @Override
    public void Initialize() {
        DoBeforeInitialize();
        try {
            DoInitialize();
            FInitialized = true;
        } finally {
            DoAfterInitialize();
        }
    }

    @Override
    public void Finalize() {
        DoBeforeFinalize();
        try {
            DoFinalize();
            FInitialized = false;
        } finally {
            DoAfterFinalize();
        }
    }

}
