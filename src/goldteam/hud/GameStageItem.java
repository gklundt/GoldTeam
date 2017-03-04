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

/**
 *
 * @author Caleb Dunham
 */
public class GameStageItem extends GameObject implements DepletableWatcher, Animatable {
    
    public int count;
    private Depletable watchedItem;
    private AnimationBase animator;
    
    public GameStageItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
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
    public void setAnimator(AnimationBase animator) {
        this.animator = animator;
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
    public void addAnimator(AnimationState state, AnimationBase animator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyAnimationChangeListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnimationBase getRemoveAnimator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
