package Uniwork.Test.Units;

import Uniwork.Misc.NGMisc;
import org.junit.Test;

import static org.junit.Assert.*;

public class NGMiscTestUnit {

    @Test
    public void testMin01() throws Exception {
        double a = 42.0;
        double b = 42.0;
        double min = NGMisc.Min(a, b);
        assertEquals(42.0, min, 0.0);
    }

    @Test
    public void testMin02() throws Exception {
        double a = 23.0;
        double b = 42.0;
        double min = NGMisc.Min(a, b);
        assertEquals(23.0, min, 0.0);
    }

    @Test
    public void testMin03() throws Exception {
        double a = 42.0;
        double b = 23.0;
        double min = NGMisc.Min(a, b);
        assertEquals(23.0, min, 0.0);
    }

    @Test
    public void testMax01() throws Exception {
        double a = 42.0;
        double b = 42.0;
        double max = NGMisc.Max(a, b);
        assertEquals(42.0, max, 0.0);
    }

    @Test
    public void testMax02() throws Exception {
        double a = 23.0;
        double b = 42.0;
        double max = NGMisc.Max(a, b);
        assertEquals(42.0, max, 0.0);
    }

    @Test
    public void testMax03() throws Exception {
        double a = 42.0;
        double b = 23.0;
        double max = NGMisc.Max(a, b);
        assertEquals(42.0, max, 0.0);
    }

    @Test
    public void testCombine01() throws Exception {
        String aPath1 = "";
        String aPath2 = "";
        String res = NGMisc.combinePath(aPath1, aPath2);
        assertEquals("", res);
    }

    @Test
    public void testCombine02() throws Exception {
        String aPath1 = "A";
        String aPath2 = "";
        String res = NGMisc.combinePath(aPath1, aPath2);
        assertEquals("A", res);
    }

    @Test
    public void testCombine03() throws Exception {
        String aPath1 = "";
        String aPath2 = "B";
        String res = NGMisc.combinePath(aPath1, aPath2);
        assertEquals("B", res);
    }

    @Test
    public void testCombine04() throws Exception {
        String aPath1 = "A";
        String aPath2 = "B";
        String res = NGMisc.combinePath(aPath1, aPath2);
        assertEquals("A/B", res);
    }

}