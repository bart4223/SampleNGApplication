package Uniwork.Test;

import Uniwork.Misc.NGStrings;
import org.junit.Test;

import static org.junit.Assert.*;

public class NGStringsTest {

    @Test
    public void testAddString01() throws Exception {
        String str = NGStrings.addString("","",".");
        assertEquals("", str);
    }

    @Test
    public void testAddString02() throws Exception {
        String str = NGStrings.addString("A","",".");
        assertEquals("A", str);
    }

    @Test
    public void testAddString03() throws Exception {
        String str = NGStrings.addString("","B",".");
        assertEquals("B", str);
    }

    @Test
    public void testAddString04() throws Exception {
        String str = NGStrings.addString("A","B",".");
        assertEquals("A.B", str);
    }

}