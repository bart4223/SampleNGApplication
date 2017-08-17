package Uniwork.Test.Units;

import Uniwork.Base.NGScriptExecuter;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NGScriptExecuterTestUnit extends NGCustomTestUnit {

    @BeforeClass
    public static void beforeSuite() {
        StartTestSuite();
    }

    @AfterClass
    public static void afterSuite() {
        FinishTestSuite();
    }

    @Test
    public void testScript01() throws Exception {
        StartTest();
        NGScriptExecuter se = new NGScriptExecuter();
        se.Initialize();
        se.Execute("// Test the Best");
        assertEquals(0, (int)se.getCommandsCalled());
        FinishTest();
    }

    @Test
    public void testScript02() throws Exception {
        StartTest();
        NGScriptExecuter se = new NGScriptExecuter();
        se.Initialize();
        se.Execute("// Test the Best\n// Coolman");
        assertEquals(0, (int)se.getCommandsCalled());
        FinishTest();
    }

    @Test
    public void testScript03() throws Exception {
        StartTest();
        NGScriptExecuter se = new NGScriptExecuter();
        se.Initialize();
        se.Execute("Application.Help");
        assertEquals(1, (int)se.getCommandsCalled());
        FinishTest();
    }

    @Test
    public void testScript04() throws Exception {
        StartTest();
        NGScriptExecuter se = new NGScriptExecuter();
        se.Initialize();
        se.Execute("Help");
        assertEquals(1, (int)se.getCommandsCalled());
        FinishTest();
    }

}
