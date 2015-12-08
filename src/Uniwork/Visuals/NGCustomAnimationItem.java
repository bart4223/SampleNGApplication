package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import javafx.animation.Animation;
import javafx.scene.Node;

import java.util.ArrayList;

public abstract class NGCustomAnimationItem extends NGObject {

    protected String FName;
    protected Animation FAnimation;
    protected Node FNode;
    protected Integer FDuration;
    protected Boolean FCyclic;
    protected Boolean FPlaying;
    protected ArrayList<NGAnimationItemEventListener> FEventListeners;

    protected void DoAnimationFinished() {
    }

    protected void AnimationFinished() {
        DoAnimationFinished();
        raiseFinishedEvent();
        if (FCyclic && FPlaying)
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

    protected void raiseFinishedEvent() {
        NGAnimationItemFinishedEvent event = new NGAnimationItemFinishedEvent(this);
        for (NGAnimationItemEventListener listener : FEventListeners)
            listener.handleItemFinished(event);
    }

    public NGCustomAnimationItem(String aName, Node aNode, Integer aDuration) {
        super();
        FEventListeners = new ArrayList<NGAnimationItemEventListener>();
        FName = aName;
        FNode = aNode;
        FDuration = aDuration;
        FAnimation = null;
        FCyclic = false;
        FPlaying = false;
    }

    public Node getNode() {
        return FNode;
    }

    public String getName() {
        return FName;
    }

    public Integer getDuration() {
        return FDuration;
    }

    public void Prepare() {
        DoPrepare();
    }

    public void Play() {
        FPlaying = true;
        DoPlay();
    }

    public void Stop() {
        FPlaying = false;
        DoStop();
    }

    public Boolean getCyclic() {
        return FCyclic;
    }

    public Boolean IsPlaying() {
        return FPlaying;
    }

    public synchronized void addEventListener(NGAnimationItemEventListener aListener)  {
        FEventListeners.add(aListener);
    }

    public synchronized void removeEventListener(NGAnimationItemEventListener aListener)   {
        FEventListeners.remove(aListener);
    }

}
