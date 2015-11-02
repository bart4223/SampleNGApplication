package Uniwork.Test;

import Uniwork.Misc.NGConsoleLogEventListener;
import Uniwork.Misc.NGLogManager;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public abstract class NGCustomTestRunner {

    protected NGConsoleLogEventListener FLogListener;
    protected Class FTestClass;
    protected NGLogManager FLogger;

    protected void DoRunTests() {
        Result result = JUnitCore.runClasses(FTestClass);
        for (Failure failure : result.getFailures())
            FLogger.writeLog(String.format("message text='Error' errorDetails='%s'", failure.toString()));
    }

    protected void DoInitTests() {

    }

    public NGCustomTestRunner(Class aTestClass) {
        FTestClass = aTestClass;
        FLogger = new NGLogManager();
        FLogListener = new NGTeamcityLogEventListener();
        FLogger.addEventListener(FLogListener);
    }

    public void InitTests() {
        DoInitTests();
    }

    public void RunTests() {
        FLogger.writeLog("message text='Test run started...'");
        try {
            DoRunTests();
        } finally {
            FLogger.writeLog("message text='Test run finished!'");
        }
    }

    public NGLogManager getLogger() {
        return FLogger;
    }

    public static NGCustomTestRunner TestRunner;

}
