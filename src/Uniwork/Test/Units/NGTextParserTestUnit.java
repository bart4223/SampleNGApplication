package Uniwork.Test.Units;

import Uniwork.Base.NGTextParser;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class NGTextParserTestUnit extends NGCustomTestUnit {

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
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("");
        assertEquals(0, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse02() throws Exception {
        StartTest();
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("A");
        assertEquals(1, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse03() throws Exception {
        StartTest();
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("AB");
        assertEquals(1, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse04() throws Exception {
        StartTest();
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("A.B");
        assertEquals(1, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse05() throws Exception {
        StartTest();
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("A B");
        assertEquals(2, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse06() throws Exception {
        StartTest();
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("A  B");
        assertEquals(2, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse07() throws Exception {
        StartTest();
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("ABC  DEF");
        assertEquals(2, (int)parser.getTokenCount());
        FinishTest();
    }

    @Test
    public void testParse08() throws Exception {
        StartTest();
        NGTextParser parser = new NGTextParser();
        parser.Initialize();
        parser.Parse("ABC  DEF   GHI");
        assertEquals(3, (int)parser.getTokenCount());
        FinishTest();
    }

}
