/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Depletable;
import goldteam.domain.DepletableWatcher;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Caleb Dunham
 */
public class LifeHudItem extends GameObject implements DepletableWatcher, Animatable {
    
    public int count;
    private Depletable watchedItem;
    private AnimationBase animator;
        private final ArrayList<ActionListener> animationChangeListeners;

    public LifeHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
                this.animationChangeListeners = new ArrayList<>();

    }
    
    @Override
    public void Update() {
        this.count = this.watchedItem.getCount();
    }
    
    @Override
    public Depletable getWatcher() {
        return this.watchedItem;
    }

    @Override
    public void setWatcher(Depletable target) {
        this.watchedItem=target;
        this.count = this.watchedItem.getCount();
        this.watchedItem.addDepletableListener(l -> Update());
    }

    @Override
    protected void GraphicsUpdateHandler() {
   
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
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animationToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
