package Uniwork.Visuals;

import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public abstract class NGCustomTransitionAnimation2DItem extends NGCustomAnimation2DItem {

    @Override
    protected void CreateAnimation() {
        FAnimation = TranslateTransitionBuilder.create()
                .node(FNode)
                .fromX(FPositionFrom.getX())
                .toX(FPositionTo.getX())
                .fromY(FPositionFrom.getY())
                .toY(FPositionTo.getY())
                .duration(Duration.millis(FDuration))
                .onFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        AnimationFinished();
                    }
                }).build();
    }

    public NGCustomTransitionAnimation2DItem(String aName, Node aNode, Integer aDuration) {
        super(aName, aNode, aDuration);
    }

}
