package Uniwork.Base;

import java.util.ArrayList;

public class NGObjectNode extends NGObject {

    protected ArrayList<NGObjectNode> FChilds;
    protected NGObjectNode FParent;

    public NGObjectNode(NGObjectNode aParent) {
        super();
        FChilds = new ArrayList<NGObjectNode>();
        FParent = aParent;
    }

    public void addChild(NGObjectNode aChild) {
        FChilds.add(aChild);
    }

    public void removeChild(NGObjectNode aChild) {
        FChilds.remove(aChild);
    }

    public void setParent(NGObjectNode aParent) {
        FParent.removeChild(this);
        FParent = aParent;
        FParent.addChild(this);
    }

    public NGObjectNode getParent() {
        return FParent;
    }

    public Integer getChildCount() {
        return FChilds.size();
    }

}
