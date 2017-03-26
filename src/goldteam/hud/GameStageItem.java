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
public class GameStageItem extends GameObject implements DepletableWatcher, Animatable {
    
    private Depletable watchedItem;
    private AnimationBase animator;
    private final ArrayList<ActionListener> animationListeners;
    
    public GameStageItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.animationListeners = new ArrayList<>();
    }
    
    @Override
    public void Update() {
        if (this.watchedItem.getCount() == 0){
            this.notifyAnimationChangeListeners(null);
        }
    }
    
    @Override
    public Depletable getWatcher() {
        return this.watchedItem;
    }

    @Override
    public void setWatcher(Depletable target) {
        this.watchedItem=target;
        this.watchedItem.addDepletableListener((l)->this.Update());
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
        this.animationListeners.add(listener);
    }

    @Override
    public void removeAnimationChangeListener(ActionListener listener) {
        this.animationListeners.remove(listener);
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animationToRemove) {
        this.animationListeners.forEach((al) -> {
            al.actionPerformed(null);
        });
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
}
