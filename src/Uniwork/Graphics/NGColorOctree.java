package Uniwork.Graphics;

import Uniwork.Base.NGObject;
import javafx.scene.paint.Color;

import java.util.Iterator;

public class NGColorOctree extends NGObject {

    protected Integer FNodeCount;
    protected Integer FLeafCount;

    protected NGColorOctreeNode FRoot;
    protected NGColorPalette FPalette;

    protected NGColorOctreeNode getLowestCompleteCountNode(NGColorOctreeNode aNode) {
        NGColorOctreeNode res = null;
        for (int i = 0; i < 8; i++) {
            NGColorOctreeNode n = aNode.getChild(i);
            if (n != null) {
                if (!n.IsLeaf() && !n.HasOnlyLeafs()) {
                    n = getLowestCompleteCountNode(n);
                }
                if (res == null || n.getCompleteCount() < res.getCompleteCount()) {
                    if (!n.IsLeaf())
                        res = n;
                }
            }
        }
        return res;
    }

    protected void BuildPalette(NGColorOctreeNode aNode) {
        for (int i = 0; i < 8; i++) {
            NGColorOctreeNode n = aNode.getChild(i);
            if (n != null) {
                if (!n.IsLeaf()) {
                    BuildPalette(n);
                }
                else {
                    FPalette.addColor(n.getRed(), n.getGreen(), n.getBlue());
                }
            }
        }
    }

    public NGColorOctree() {
        super();
        FRoot = new NGColorOctreeNode();
        FPalette = new NGColorPalette();
        FNodeCount = 1;
        FLeafCount = 0;
    }

    public Integer getNodeCount() {
        return FNodeCount;
    }

    public Integer getLeafCount() {
        return FLeafCount;
    }

    public NGColorOctreeNode getLowestCompleteCountNode() {
        return getLowestCompleteCountNode(FRoot);
    }

    public void addColor(Integer aRed, Integer aGreen, Integer aBlue) {
        NGColorOctreeNode node = FRoot;
        for (int i = 7; i >= 0; i--) {
            Integer Index = 0;
            Integer Bit = 1 << i;
            if ((aRed & Bit) != 0) {
                Index = Index | 4;
            }
            if ((aGreen & Bit) != 0) {
                Index = Index | 2;
            }
            if ((aBlue & Bit) != 0) {
                Index = Index | 1;
            }
            NGColorOctreeNode newnode = node.getChild(Index);
            if (newnode == null) {
                if (i == 0) {
                    newnode = new NGColorOctreeNode(aRed, aGreen, aBlue);
                    newnode.addCount();
                    FLeafCount = FLeafCount + 1;
                }
                else {
                    newnode = new NGColorOctreeNode();
                }
                node.addChild(Index, newnode);
                FNodeCount = FNodeCount + 1;
            } else if (i == 0) {
                newnode.addCount();
            }
            node = newnode;
        }
    }

    public void Quantize(Integer aCount) {
        while (FLeafCount > aCount) {
            NGColorOctreeNode n = getLowestCompleteCountNode();
            if (n == null) {
                break;
            }
            if (n.HasOnlyLeafs()) {
                for (int i = 0; i < 8; i++) {
                    NGColorOctreeNode child = n.getChild(i);
                    if (child != null) {
                        n.setRed((((n.getRed() * n.getCount()) + (child.getRed() * child.getCount())) / (n.getCount() + child.getCount())));
                        n.setGreen((((n.getGreen() * n.getCount()) + (child.getGreen() * child.getCount())) / (n.getCount() + child.getCount())));
                        n.setBlue((((n.getBlue() * n.getCount()) + (child.getBlue() * child.getCount())) / (n.getCount() + child.getCount())));
                        n.addCount(child.getCount());
                        n.clearChild(i);
                        FLeafCount = FLeafCount - 1;
                        FNodeCount = FNodeCount - 1;
                    }
                }
                n.UpdateColor();
                FLeafCount = FLeafCount + 1;
            }
        }
    }

    public void RebuildPalette() {
        BuildPalette();
    }

    public void BuildPalette() {
        FPalette.Clear();
        BuildPalette(FRoot);
    }

    public Integer getPaletteCount() {
        return FPalette.getCount();
    }

    public Iterator<NGColorPaletteItem> getPalette() {
        return FPalette.getIterator();
    }

    public Color getNearestColorInPalette(Color aColor) {
        return FPalette.getNearestColor(aColor);
    }

    public Color getNodeColorFromColor(Color aColor) {
        NGColorOctreeNode res = FRoot;
        Integer red = (int)(aColor.getRed() * 255);
        Integer green = (int)(aColor.getGreen() * 255);
        Integer blue = (int)(aColor.getBlue() * 255);
        for (int i = 7; i >= 0; i--) {
            Integer Index = 0;
            Integer Bit = 1 << i;
            if ((red & Bit) != 0) {
                Index = Index | 4;
            }
            if ((green & Bit) != 0) {
                Index = Index | 2;
            }
            if ((blue & Bit) != 0) {
                Index = Index | 1;
            }
            NGColorOctreeNode child = res.getChild(Index);
            if (child != null) {
                res = child;
                if (res.IsLeaf()) {
                    break;
                }
            }
        }
        return res.getColor();
    }

}
