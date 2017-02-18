package goldteam.domain;

import java.awt.event.ActionListener;

public interface Animatable {

    /**
     * Use add Animator instead
     * @param animator
     * @deprecated
     */
    @Deprecated
    public void setAnimator(AnimationBase animator);

    public AnimationBase getAnimator();

    public void addAnimationTimerListener(ActionListener listener);
    
    public void addAnimationChangeListener(ActionListener listener);
    
    public void notifyAnimationChangeListeners();
    
    public void addAnimator(AnimationState state, AnimationBase animator);
    
    public AnimationBase getRemoveAnimator();
}
