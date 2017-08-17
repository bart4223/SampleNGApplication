package Uniwork.Test.Units;

import Uniwork.Base.NGScriptParser;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NGScriptParserTestUnit extends NGCustomTestUnit {

    @BeforeClass
    public static void beforeSuite() {
        StartTestSuite();
    }

    @AfterClass
    public static void afterSuite() {
        FinishTestSuite();
    }

    @Test
    public void testParse01() throws Exception {
        StartTest();
        NGScriptParser parser = new NGScriptParser();
        parser.Initialize();
        parser.Parse("// Test the Best");
        assertEquals(1, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse02() throws Exception {
        StartTest();
        NGScriptParser parser = new NGScriptParser();
        parser.Initialize();
        parser.Parse("Application.Quit");
        assertEquals(1, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse03() throws Exception {
        StartTest();
        NGScriptParser parser = new NGScriptParser();
        parser.Initialize();
        parser.Parse("Base.add 4 2");
        assertEquals(3, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse04() throws Exception {
        StartTest();
        NGScriptParser parser = new NGScriptParser();
        parser.Initialize();
        parser.Parse("Base.neg :Test");
        assertEquals(2, (int)parser.getTokenCount());
        FinishTest();
    }

}
