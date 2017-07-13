package Uniwork.Visuals;

import Uniwork.Base.NGComponent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public Iterator<Node> getChildNodes(Parent aParent, String aID) {
        CopyOnWriteArrayList<Node> res = new CopyOnWriteArrayList<Node>();
        for (Node child : aParent.getChildrenUnmodifiable()) {
            if (child.getId().equals(aID)) {
                res.add(child);
            }
        }
        return res.iterator();
    }

    public Group addGroup() {
        return addGroup(null);
    }

    public Group addGroup(Node aNode) {
        return addGroup(aNode, 0.0, 0.0);
    }

    public Group addGroup(Node aNode, Double aX, Double aY) {
        Group group = new Group();
        group.setTranslateX(aX);
        group.setTranslateY(aY);
        if (aNode != null)
            group.getChildren().add(aNode);
        FRoot.getChildren().add(group);
        return group;
    }

    public void addNode(Group aGroup, Node aNode) {
        aGroup.getChildren().add(aNode);
    }

}
