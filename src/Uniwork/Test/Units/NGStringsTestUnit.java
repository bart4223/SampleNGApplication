package Uniwork.Test.Units;

import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGStrings;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class NGStringsTestUnit extends NGCustomTestUnit {

    @BeforeClass
    public static void beforeSuite() {
        StartTestSuite();
    }

    @AfterClass
    public static void afterSuite() {
        FinishTestSuite();
    }

    @Test
    public void testAddString01() throws Exception {
        StartTest();
        String str = NGStrings.addString("","",".");
        assertEquals("", str);
        FinishTest();
    }

    @Test
    public void testAddString02() throws Exception {
        StartTest();
        String str = NGStrings.addString("A","",".");
        assertEquals("A", str);
        FinishTest();
    }

    @Test
    public void testAddString03() throws Exception {
        StartTest();
        String str = NGStrings.addString("","B",".");
        assertEquals("B", str);
        FinishTest();
    }

    @Test
    public void testAddString04() throws Exception {
        StartTest();
        String str = NGStrings.addString("A","B",".");
        assertEquals("A.B", str);
        FinishTest();
    }

    @Test
    public void testGetStringCount01() throws Exception {
        StartTest();
        String str = "";
        assertEquals(1, (int)NGStrings.getStringCount(str,"\\."));
        FinishTest();
    }

    @Test
    public void testGetStringCount02() throws Exception {
        StartTest();
        String str = "a";
        assertEquals(1, (int)NGStrings.getStringCount(str,"\\."));
        FinishTest();
    }

    @Test
    public void testGetStringCount03() throws Exception {
        StartTest();
        String str = NGStrings.addString("A","B",".");
        assertEquals(2, (int)NGStrings.getStringCount(str,"\\."));
        FinishTest();
    }

    @Test
    public void testDate01() throws Exception {
        StartTest();
        Date date = new Date();
        String str = NGStrings.getDateAsString(date, NGLogEntry.FMT_STD_DATE);
        assertEquals(true, str.length() > 0);
        FinishTest();
    }

    @Test
    public void testDate02() throws Exception {
        StartTest();
        Date date = new Date(0);
        String str = NGStrings.getDateAsString(date, NGLogEntry.FMT_STD_DATE);
        assertEquals(true, str.length() > 0);
        FinishTest();
    }

    @Test
    public void testDate03() throws Exception {
        StartTest();
        Date date = new Date();
        String str = NGStrings.getUTCDateAsString(date);
        assertEquals(true, str.length() > 0);
        FinishTest();
    }

    @Test
    public void testDate04() throws Exception {
        StartTest();
        Date date = new Date();
        String str = NGStrings.getUTCDateAsString(date);
        Date date2 = NGStrings.getUTCDateFromString(str);
        String str2 = NGStrings.getUTCDateAsString(date2);
        assertEquals(true, str2.length() > 0);
        FinishTest();
    }

}