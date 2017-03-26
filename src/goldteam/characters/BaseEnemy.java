/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.characters;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Enemy;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Joshua
 */
public abstract class BaseEnemy extends GameObject implements Animatable, Enemy {

    protected AnimationBase animator;
    protected final ArrayList<ActionListener> animationChangeListeners;

    public BaseEnemy(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.animationChangeListeners = new ArrayList<>();
    }

    @Override
    protected void GraphicsUpdateHandler() {
        Update();
    }

    @Override
    protected void UpdateEffectHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnimationBase getAnimator() {
        return this.animator;
    }

    @Override
    public void addAnimationTimerListener(ActionListener listener) {
        this.gamedata.addAnimationUpdateTimerListener(listener);
    }

    @Override
    public void addAnimationChangeListener(ActionListener listener) {
        this.animationChangeListeners.add(listener);
    }

    @Override
    public void removeAnimationChangeListener(ActionListener listener) {
        this.animationChangeListeners.remove(listener);
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        ActionEvent e = new ActionEvent(animatorToRemove, 0, null);
        for (ActionListener animationChangeListener : animationChangeListeners) {
            animationChangeListener.actionPerformed(e);
        }
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }

}
