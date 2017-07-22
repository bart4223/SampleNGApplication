package Uniwork.Test.Units;

import Uniwork.Graphics.NGColorOctree;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NGOctreeTestUnit extends NGCustomTestUnit {

    @BeforeClass
    public static void beforeSuite() {
        StartTestSuite();
    }

    @AfterClass
    public static void afterSuite() {
        FinishTestSuite();
    }

    @Test
    public void testAddColor01() throws Exception {
        StartTest();
        NGColorOctree ot = new NGColorOctree();
        for (int i = 0; i < 10; i++) {
            ot.addColor(1, 1, 1);
        }
        assertEquals(1, (int)ot.getLeafCount());
        FinishTest();
    }

    @Test
    public void testAddColor02() throws Exception {
        StartTest();
        NGColorOctree ot = new NGColorOctree();
        for (int i = 0; i < 10; i++) {
            ot.addColor(i, i, i);
        }
        assertEquals(10, (int)ot.getLeafCount());
        FinishTest();
    }

    @Test
    public void testBuildPalette01() throws Exception {
        StartTest();
        NGColorOctree ot = new NGColorOctree();
        for (int i = 0; i < 10; i++) {
            ot.addColor(1, 1, 1);
        }
        assertEquals(1, (int)ot.getLeafCount());
        ot.BuildPalette();
        assertEquals(1, (int)ot.getPaletteCount());
        FinishTest();
    }

    @Test
    public void testBuildPalette02() throws Exception {
        StartTest();
        NGColorOctree ot = new NGColorOctree();
        for (int i = 0; i < 10; i++) {
            ot.addColor(i, i, i);
        }
        assertEquals(10, (int)ot.getLeafCount());
        ot.BuildPalette();
        assertEquals(10, (int)ot.getPaletteCount());
        FinishTest();
    }

}
