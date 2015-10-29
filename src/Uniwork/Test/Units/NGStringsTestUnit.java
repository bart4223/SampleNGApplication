package Uniwork.Test.Units;

import Uniwork.Misc.NGStrings;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class NGStringsTestUnit extends NGCustomTestUnit {

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

}