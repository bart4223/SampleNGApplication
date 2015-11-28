package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import javafx.animation.Animation;
import javafx.scene.Node;

public abstract class NGCustomAnimationItem extends NGObject {

    protected String FName;
    protected Animation FAnimation;
    protected Node FNode;
    protected Double FDuration;
    protected Boolean FCyclic;

    protected void DoAnimationFinished() {

    }

    protected void AnimationFinished() {
        DoAnimationFinished();
        if (FCyclic)
            DoPlay();
    }

    protected void CreateAnimation() {

    }

    protected void DoPrepare() {
        CreateAnimation();
    }

    protected void DoPlay() {
        DoPrepare();
        FAnimation.play();
    }

    protected void DoStop() {
        FAnimation.stop();
    }

    public NGCustomAnimationItem(String aName, Node aNode, Double aDuration) {
        super();
        FName = aName;
        FNode = aNode;
        FDuration = aDuration;
        FAnimation = null;
        FCyclic = false;
    }

    public Node getNode() {
        return FNode;
    }

    public String getName() {
        return FName;
    }

    public Double getDuration() {
        return FDuration;
    }

    public void Prepare() {
        DoPrepare();
    }

    public void Play() {
        DoPlay();
    }

    public void Stop() {
        DoStop();
    }

    public Boolean getCyclic() {
        return FCyclic;
    }

}
