/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Attackable;
import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;
import java.awt.event.ActionListener;

/**
 *
 * @author faaez
 */
public class TestHudItem extends GameObject implements AttackableWatcher, Animatable{

    public int count;
    private Attackable watchedItem;
    private AnimationBase animator;
    
    public TestHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
    }

    @Override
    protected void Update() {
        this.count = this.watchedItem.getHealthValue();
    }

    @Override
    protected void GraphicsUpdateHandler() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Attackable getWatcher() {
        return this.watchedItem;
    }

    @Override
    public void setWatcher(Attackable target) {
        this.watchedItem=target;
        this.count = this.watchedItem.getHealthValue();
        this.watchedItem.addAttackableListener(l -> Update());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAnimationChangeListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animationToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }

    
}
