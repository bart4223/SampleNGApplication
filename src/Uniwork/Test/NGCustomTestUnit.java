package Uniwork.Test;

import java.util.Date;
import Uniwork.Base.NGObjectStack;
import Uniwork.Misc.NGMisc;
import Uniwork.Misc.NGStrings;

public abstract class NGCustomTestUnit {

    public final static String FMT_STD_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    protected static Integer FStackIndex = 4;

    protected NGObjectStack FTests;

    protected static void writeLog(String aText) {
        NGCustomTestRunner.TestRunner.getLogger().writeLog(aText);
    }

    protected static void DoStartTestSuite() {
        writeLog(String.format("testSuiteStarted name='%s' timestamp='%s'", NGMisc.getCallStackElementByIndex(FStackIndex).getClassName(), NGStrings.getDateAsString(new Date(), FMT_STD_DATE)));
    }

    protected static void DoFinishTestSuite() {
        writeLog(String.format("testSuiteFinished name='%s' timestamp='%s'", NGMisc.getCallStackElementByIndex(FStackIndex).getClassName(), NGStrings.getDateAsString(new Date(), FMT_STD_DATE)));
    }

    protected void DoStartTest() throws Exception {
        if (!FTests.isEmpty())
            throw new Exception();
        FTests.push(NGMisc.getCallStackElementByIndex(FStackIndex).getMethodName());
        writeLog(String.format("testStarted name='%s' timestamp='%s'", FTests.Top(), NGStrings.getDateAsString(new Date(), FMT_STD_DATE)));
    }

    protected void DoFinishTest() throws Exception {
        String method = (String)FTests.pop();
        if (method != NGMisc.getCallStackElementByIndex(FStackIndex).getMethodName())
            throw new Exception();
        writeLog(String.format("testFinished name='%s' timestamp='%s'", method, NGStrings.getDateAsString(new Date(), FMT_STD_DATE)));
    }

    protected void StartTest() throws Exception {
        DoStartTest();
    }

    protected void FinishTest() throws Exception {
        DoFinishTest();
    }

    protected static void StartTestSuite() {
        DoStartTestSuite();
    }

    protected static void FinishTestSuite() {
        DoFinishTestSuite();
    }

    public NGCustomTestUnit() {
        FTests = new NGObjectStack();
    }

}
