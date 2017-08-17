package Uniwork.Base;

public class NGObjectTree extends NGObject {

    protected NGObjectNode FRoot;

    public NGObjectTree() {
        super();
        FRoot = new NGObjectNode(null);
    }

    public NGObjectNode getRoot() {
        return FRoot;
    }

    public void RemoveAll() {
        FRoot.removeChilds();
    }

}
