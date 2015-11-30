package Uniwork.Visuals;

import Uniwork.Base.NGComponent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class NGStageSceneGraph extends NGComponent {

    protected AnchorPane FRoot;

    public NGStageSceneGraph() {
        this(null);
    }

    public NGStageSceneGraph(NGComponent aOwner) {
        this(aOwner, "");
    }

    public NGStageSceneGraph(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FRoot = null;
    }

    public Parent getRoot() {
        return FRoot;
    }

    public void setRoot(AnchorPane aRoot) {
        FRoot = aRoot;
    }

    public Node getChildNode(Parent aParent, String aID) {
        for (Node child : aParent.getChildrenUnmodifiable()) {
            if (child.getId().equals(aID)) {
                return child;
            }
        }
        return null;
    }

    public Group addGroup() {
        return this.addGroup(null);
    }

    public Group addGroup(Node aNode) {
        Group group;
        if (aNode != null)
            group = new Group(aNode);
        else
            group = new Group();
        FRoot.getChildren().add(group);
        return group;
    }

    public void addNode(Group aGroup, Node aNode) {
        aGroup.getChildren().add(aNode);
    }

}
