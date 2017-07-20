package Uniwork.Graphics;

import Uniwork.Base.NGObject;

public class NGColorOctreeNode extends NGObject {

    protected Integer FCount;
    protected Integer FRed;
    protected Integer FGreen;
    protected Integer FBlue;
    protected NGColorOctreeNode[] FChilds;

    public NGColorOctreeNode() {
        this(0,0,0);
    }

    public NGColorOctreeNode(Integer aRed, Integer aGreen, Integer aBlue) {
        super();
        FCount = 0;
        FRed = aRed;
        FGreen = aGreen;
        FBlue = aBlue;
        FChilds = new NGColorOctreeNode[8];
        for (int i = 0; i < 8; i++) {
            FChilds[i] = null;
        }
    }

    public NGColorOctreeNode getChild(Integer aIndex) {
        return FChilds[aIndex];
    }

    public void clearChild(Integer aIndex) {
        FChilds[aIndex] = null;
    }

    public void addChild(Integer aIndex, NGColorOctreeNode aNode) {
        FChilds[aIndex] = aNode;
    }

    public void addCount() {
        addCount(1);
    }

    public void addCount(Integer aCount) {
        FCount = FCount + aCount;
    }

    public Integer getCount() {
        return FCount;
    }

    public Integer getChildCount() {
        Integer count = 0;
        for (int i = 0; i < 8; i++) {
            if (FChilds[i] != null)
                count = count + 1;
        }
        return count;
    }

    public Integer getCompleteCount() {
        Integer count = 0;
        for (int i = 0; i < 8; i++) {
            if (FChilds[i] != null)
                count = count + FChilds[i].getCount();
        }
        return count;
    }

    public Boolean HasOnlyLeafs() {
        Boolean res = true;
        for (int i = 0; i < 8; i++) {
            if (FChilds[i] != null && res) {
                res = FChilds[i].IsLeaf();
            }
        }
        return res;
    }

    public Boolean HasChilds() {
        return getChildCount() > 0;
    }

    public Boolean IsLeaf() {
        return !HasChilds();
    }

    public void setRed(Integer aRed) {
        FRed = aRed;
    }

    public void setGreen(Integer aGreen) {
        FGreen = aGreen;
    }

    public void setBlue(Integer aBlue) {
        FBlue = aBlue;
    }

    public Integer getRed() {
        return FRed;
    }

    public Integer getGreen() {
        return FGreen;
    }

    public Integer getBlue() {
        return FBlue;
    }

}
