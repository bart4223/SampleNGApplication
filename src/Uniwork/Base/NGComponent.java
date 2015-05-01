package Uniwork.Base;

import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogManager;

public class NGComponent extends NGObject implements NGInitializable {

    protected Boolean FInitialized;
    protected NGLogManager FLogManager;

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
        if (FLogManager != null) {
            FLogManager.writeLog(aInfo, NGLogEntry.LogType.Info);
        }
    }

    protected void writeWarning(String aWarning) {
        if (FLogManager != null) {
            FLogManager.writeLog(aWarning, NGLogEntry.LogType.Warning);
        }
    }

    protected void writeError(String aError) {
        if (FLogManager != null) {
            FLogManager.writeLog(aError, NGLogEntry.LogType.Error);
        }
    }

    public NGComponent() {
        super();
        FLogManager = null;
        FInitialized = false;
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
            DoAfterInitialize();
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
            DoAfterFinalize();
        } finally {
            DoAfterFinalize();
        }
    }

}
