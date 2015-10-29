package Uniwork.Test;

import Uniwork.Base.NGObjectStack;
import Uniwork.Misc.NGMisc;

public abstract class NGCustomTestUnit {

    protected static Integer FStackIndex = 4;

    protected NGObjectStack FTests;

    protected static void writeLog(String aText) {
        NGTestRunner.Logger.writeLog(aText);
    }

    protected static void DoStartTestSuite() {
        writeLog(String.format("testSuiteStarted name='%s'", NGMisc.getCallStackElementByIndex(FStackIndex).getClassName()));
    }

    protected static void DoFinishTestSuite() {
        writeLog(String.format("testSuiteFinished name='%s'", NGMisc.getCallStackElementByIndex(FStackIndex).getClassName()));
    }

    protected void DoStartTest() throws Exception {
        if (!FTests.isEmpty())
            throw new Exception();
        FTests.push(NGMisc.getCallStackElementByIndex(FStackIndex).getMethodName());
        writeLog(String.format("testStarted name='%s'", FTests.Top()));
    }

    protected void DoFinishTest() throws Exception {
        String method = (String)FTests.pop();
        if (method != NGMisc.getCallStackElementByIndex(FStackIndex).getMethodName())
            throw new Exception();
        writeLog(String.format("testFinished name='%s'", method));
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
