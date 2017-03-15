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
 * @author Caleb Dunham
 */
public class ArrowChargeIndicator extends GameObject implements 
            Animatable,         /* Will be drawn on screen */
            AttackableWatcher  /* Watches the archer */
            {
    private double currentCharge;
    private Attackable watchedItem;
    private AnimationBase animator;
    
    public ArrowChargeIndicator(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
    }

    @Override
    protected void Update() {
        this.currentCharge = this.watchedItem.getChargeValue();
    }

    @Override
    protected void GraphicsUpdateHandler() {
        
    }

    @Override
    protected void ClickHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void KeyHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void setAnimator(AnimationBase animator) {
        this.animator = animator;
    }

    @Override
    public AnimationBase getAnimator() {
        return this.animator;
    }

    public double getCurrentCharge() {
        return currentCharge;
    }

    public void setCurrentCharge(double currentCharge) {
        this.currentCharge = currentCharge;
    }

    public Attackable getWatchedItem() {
        return watchedItem;
    }

    public void setWatchedItem(Attackable watchedItem) {
        this.watchedItem = watchedItem;
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
    public void notifyAnimationChangeListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnimationBase getRemoveAnimator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setWatcher(Attackable target) {
        this.watchedItem = target;
        this.currentCharge = this.watchedItem.getChargeValue();
        this.watchedItem.addAttackableListener(l -> Update());
    }

    @Override
    public Attackable getWatcher() {
        return this.watchedItem;
    }
    
}
