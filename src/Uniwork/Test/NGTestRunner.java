package Uniwork.Test;

import Uniwork.Misc.NGConsoleLogEventListener;
import Uniwork.Misc.NGLogManager;
import Uniwork.Test.Units.NGAllTestUnits;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class NGTestRunner {

    protected static NGConsoleLogEventListener FLogListener;

    public static void main(String[] args) {
        FLogListener = new NGTeamcityLogEventListener();
        Logger = new NGLogManager();
        Logger.addEventListener(FLogListener);
        Logger.writeLog("message text='Test run started...'");
        Result result = JUnitCore.runClasses(NGAllTestUnits.class);
        for (Failure failure : result.getFailures()) {
            Logger.writeLog(String.format("message text='Error' errorDetails='%s'", failure.toString()));
        }
        Logger.writeLog("message text='Test run finished!'");
    }

    public static NGLogManager Logger;

}
