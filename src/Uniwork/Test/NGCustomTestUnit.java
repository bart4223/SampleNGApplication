package Uniwork.Test;

import Uniwork.Base.NGObjectStack;
import Uniwork.Misc.NGMisc;

public abstract class NGCustomTestUnit {

    protected static Integer FStackIndex = 4;

    protected NGObjectStack FTests;

    protected void writeLog(String aText) {
        NGTestRunner.Logger.writeLog(aText);
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

    public NGCustomTestUnit() {
        FTests = new NGObjectStack();
    }

    public void StartTest() throws Exception {
        DoStartTest();
    }

    public void FinishTest() throws Exception {
        DoFinishTest();
    }

}
