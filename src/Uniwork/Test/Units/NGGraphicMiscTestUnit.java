package Uniwork.Test.Units;

import Uniwork.Graphics.NGGraphicMisc;
import Uniwork.Misc.NGStrings;
import Uniwork.Test.NGCustomTestUnit;
import javafx.scene.paint.Color;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NGGraphicMiscTestUnit extends NGCustomTestUnit {

    @BeforeClass
    public static void beforeSuite() {
        StartTestSuite();
    }

    @AfterClass
    public static void afterSuite() {
        FinishTestSuite();
    }

    @Test
    public void testcolorToHex01() throws Exception {
        StartTest();
        String str = NGGraphicMisc.colorToHex(Color.BLACK);
        assertEquals("000000ff", str);
        FinishTest();
    }

    @Test
    public void testcolorToHex02() throws Exception {
        StartTest();
        String str = NGGraphicMisc.colorToHex(Color.WHITE);
        assertEquals("ffffffff", str);
        FinishTest();
    }

    @Test
    public void testcolorToHex03() throws Exception {
        StartTest();
        String str = NGGraphicMisc.colorToHex(Color.RED);
        assertEquals("ff0000ff", str);
        FinishTest();
    }

    @Test
    public void testcolorToHex04() throws Exception {
        StartTest();
        String str = NGGraphicMisc.colorToHex(Color.rgb(0, 255, 0));
        assertEquals("00ff00ff", str);
        FinishTest();
    }

    @Test
    public void testcolorToHex05() throws Exception {
        StartTest();
        String str = NGGraphicMisc.colorToHex(Color.BLUE);
        assertEquals("0000ffff", str);
        FinishTest();
    }

}
