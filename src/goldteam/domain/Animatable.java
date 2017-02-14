package goldteam.domain;

import java.awt.event.ActionListener;

public interface Animatable {

    public void setAnimator(AnimationBase animator);

    public AnimationBase getAnimator();

    public void addAnimationTimerListener(ActionListener listener);
}
