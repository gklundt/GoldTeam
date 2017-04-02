/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Boostable;
import goldteam.domain.BoostableWatcher;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author faaez
 */
public class BoostStatusBar extends GameObject implements Animatable, BoostableWatcher{
    
    private AnimationBase animator;
    private Boostable watchedItem;
    public boolean draw;
    private final ArrayList<ActionListener> animationChangeListeners;

    public BoostStatusBar(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.animationChangeListeners = new ArrayList<>();
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
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }


    @Override
    public Boostable getWatcher() {
        return this.watchedItem;
    }

    @Override
    public void setWatcher(Boostable target) {
        this.watchedItem = target;
        this.draw = this.watchedItem.isBoostableWeapon();
        this.watchedItem.addBoostableListener(l -> Update());
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void Update() {
    
    }

    @Override
    protected void GraphicsUpdateHandler() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void UpdateEffectHandler() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
