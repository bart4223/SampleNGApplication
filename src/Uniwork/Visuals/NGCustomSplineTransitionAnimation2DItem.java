package Uniwork.Visuals;

import com.sun.scenario.animation.SplineInterpolator;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class NGCustomSplineTransitionAnimation2DItem extends NGCustomTransitionAnimation2DItem {

    protected SplineInterpolator FSplineInterpolator;

    @Override
    protected void DoBeforePrepare() {
        super.DoBeforePrepare();
        FSplineInterpolator = (SplineInterpolator)Interpolator.SPLINE(0.3, 0.3, 0.6, 0.6);
        // ToDo
        //FInterpolator.curve()
    }

    @Override
    protected void DoCreateAnimation() {
        FAnimation = TranslateTransitionBuilder.create()
                .node(FNode)
                .fromX(FPositionFrom.getX())
                .toX(FPositionTo.getX())
                .fromY(FPositionFrom.getY())
                .toY(FPositionTo.getY())
                .duration(Duration.millis(FDuration))
                .interpolator(FSplineInterpolator)
                .onFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        AnimationFinished();
                    }
                }).build();
    }

    public NGCustomSplineTransitionAnimation2DItem(String aName, Node aNode, Integer aDuration) {
        super(aName, aNode, aDuration);
        FSplineInterpolator = null;
    }

}
