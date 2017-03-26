package goldteam.domain;

import java.awt.event.ActionListener;

public interface Animatable {

    /**
     * Gets the current Animator for the Animatable
     * Used by the game panel switch animation routine
     * @return
     */
    public AnimationBase getAnimator();

    /**
     * Adds animator action listener interface to the
     * game engine animation timer on behalf of the animator
     * Used on animator creation after adding the animator to 
     * the animatable
     * create animatable (e.g. ArcherMan)
     * create animator(s) (e.g. all animations for ArcherMan action states)
     * add animator(s) to animatable
     * add animators to game engine animation timer listener via this method
     * @param listener
     */
    public void addAnimationTimerListener(ActionListener listener);
    
    /**
     * Listener interface for animation change
     * Game Panel switch animation registers with this event
     * @param listener
     */
    public void addAnimationChangeListener(ActionListener listener);
    
    /**
     * Listener interface for animation change
     * Game Panel switch animation registers with this event
     * @param listener
     */
    public void removeAnimationChangeListener(ActionListener listener);
    
    /**
     * Notifies listeners attached to animation change listener when this
     * animation state changes
     */
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove);
    
    /**
     * List of animations associated with this animatable object
     * @param state
     * @param animator
     */
    public void addAnimator(AnimationState state, AnimationBase animator);
    
    /**
     * Used by game panel to remove animations
     * when the game panel is notified that the animation changed
     * it gets the remove animator, removes it
     * then gets the add animator and adds it
     * 
     * Possible better implementation is to create separate 
     * add and remove listeners to avoid situations where the animations
     * are changed faster than the game panel can get the references
     * This is a possible race condition
     * @return
     */
}
