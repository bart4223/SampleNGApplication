package Uniwork.Test;

import Uniwork.Test.Units.NGAllTestUnits;

public class NGTestRunner extends NGCustomTestRunner {

    public NGTestRunner(Class aTestClass) {
        super(aTestClass);
    }

    public static void main(String[] args) {
        TestRunner = new NGTestRunner(NGAllTestUnits.class);
        TestRunner.InitTests();
        TestRunner.RunTests();
    }

}
