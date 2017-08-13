package Uniwork.Test.Units;

import Uniwork.Base.NGObjectNode;
import Uniwork.Base.NGObjectTree;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NGObjectTreeTestUnit extends NGCustomTestUnit {

    @BeforeClass
    public static void beforeSuite() {
        StartTestSuite();
    }

    @AfterClass
    public static void afterSuite() {
        FinishTestSuite();
    }

    @Test
    public void testgetRoot() throws Exception {
        StartTest();
        NGObjectTree tree = new NGObjectTree();
        NGObjectNode root = tree.getRoot();
        assertEquals(true, root != null);
        assertEquals(true, root.getParent() == null);
        FinishTest();
    }

    @Test
    public void testaddChild01() throws Exception {
        StartTest();
        NGObjectTree tree = new NGObjectTree();
        NGObjectNode root = tree.getRoot();
        NGObjectNode node = new NGObjectNode(root);
        root.addChild(node);
        assertEquals(1, (int)root.getChildCount());
        assertEquals(true, node.getParent() == root);
        FinishTest();
    }

}
