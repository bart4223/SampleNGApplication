package Uniwork.Visuals;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public abstract class NGCustomTimeLineAnimationItem extends NGCustomAnimationItem {

    @Override
    protected void DoCreateAnimation() {
        FAnimation = TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(
                        new KeyFrame(Duration.millis(FDuration), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                AnimationFinished();
                            }
                        })
                )
                .build();
    }

    public NGCustomTimeLineAnimationItem(String aName, Node aNode, Integer aDuration) {
        super(aName, aNode, aDuration);
    }

}
