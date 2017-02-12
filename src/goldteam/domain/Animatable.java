/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

/**
 * Animateable
 * @author gordon
 */
public interface Animatable {
    public void setAnimator(AnimationBase animator);
    public AnimationBase getAnimator();
}
