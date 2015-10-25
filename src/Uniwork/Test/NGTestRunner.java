package Uniwork.Test;

import Uniwork.Misc.NGConsoleLogEventListener;
import Uniwork.Misc.NGLogManager;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class NGTestRunner {

    protected static NGConsoleLogEventListener FLogListener;

    public static void main(String[] args) {
        FLogListener = new NGConsoleLogEventListener(NGConsoleLogEventListener.mode.teamcity);
        Logger = new NGLogManager();
        Logger.addEventListener(FLogListener);
        Logger.writeLog("message text='Test run started...'");
        Result result = JUnitCore.runClasses(NGAllTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        Logger.writeLog("message text='Test run finished!'");
    }

    public static NGLogManager Logger;

}
