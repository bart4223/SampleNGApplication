package Uniwork.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class NGTestRunner {

    public static void main(String[] args) {
        System.out.println("Test run started...");
        Result result = JUnitCore.runClasses(NGAllTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Test run finished!");
    }

}
