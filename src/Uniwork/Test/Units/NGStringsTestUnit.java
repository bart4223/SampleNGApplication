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

    @Test
    public void testDuration01() throws Exception {
        StartTest();
        String str = NGStrings.getDurationAsString(0);
        assertEquals("00:00:00", str);
        FinishTest();
    }

    @Test
    public void testDuration02() throws Exception {
        StartTest();
        String str = NGStrings.getDurationAsString(1);
        assertEquals("00:00:01", str);
        FinishTest();
    }

    @Test
    public void testDuration03() throws Exception {
        StartTest();
        String str = NGStrings.getDurationAsString(60);
        assertEquals("00:01:00", str);
        FinishTest();
    }

    @Test
    public void testDuration04() throws Exception {
        StartTest();
        String str = NGStrings.getDurationAsString(3600);
        assertEquals("01:00:00", str);
        FinishTest();
    }

    @Test
    public void testDuration05() throws Exception {
        StartTest();
        String str = NGStrings.getDurationAsString(3661);
        assertEquals("01:01:01", str);
        FinishTest();
    }

    @Test
    public void testDuration06() throws Exception {
        StartTest();
        String str = NGStrings.getDurationAsString(86400);
        assertEquals("1 Tag(e) 00:00:00", str);
        FinishTest();
    }

    @Test
    public void testDuration07() throws Exception {
        StartTest();
        String str = NGStrings.getDurationAsString(86400 * 2 + 3661);
        assertEquals("2 Tag(e) 01:01:01", str);
        FinishTest();
    }

    @Test
    public void testByteArrayToHex01() throws Exception {
        StartTest();
        byte[] ba = new byte[3];
        ba[0] = (byte)255;
        ba[1] = (byte)118;
        ba[2] = (byte)201;
        String str = NGStrings.byteArrayToHexString(ba);
        assertEquals("FF76C9", str);
        FinishTest();
    }

    @Test
    public void testHexToByteArray01() throws Exception {
        StartTest();
        String str = "FF76C9";
        byte[] ba = NGStrings.hexStringToByteArray(str);
        assertEquals(3, ba.length);
        assertEquals((byte)255, ba[0]);
        assertEquals((byte)118, ba[1]);
        assertEquals((byte)201, ba[2]);
        FinishTest();
    }

    @Test
    public void testencryptPassword01() throws Exception {
        StartTest();
        String pw = NGStrings.encryptPassword("Cool");
        assertEquals("F17ED04D10A1D9EF3AA8092AE8C98A11", pw);
        FinishTest();
    }

    @Test
    public void testdecryptPassword01() throws Exception {
        StartTest();
        String pw = NGStrings.decryptPassword("F17ED04D10A1D9EF3AA8092AE8C98A11");
        assertEquals("Cool", pw);
        FinishTest();
    }

}