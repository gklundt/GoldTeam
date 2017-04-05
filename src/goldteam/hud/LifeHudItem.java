/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
<<<<<<< HEAD
import goldteam.domain.Attackable;
import goldteam.domain.AttackableWatcher;
=======
import goldteam.domain.Depletable;
import goldteam.domain.DepletableWatcher;
>>>>>>> dev
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;
import java.awt.event.ActionListener;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> dev

/**
 *
 * @author Caleb Dunham
 */
<<<<<<< HEAD
public class LifeHudItem extends GameObject implements AttackableWatcher, Animatable {
    
    public int count;
    private Attackable watchedItem;
    private AnimationBase animator;
    
    public LifeHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
=======
public class LifeHudItem extends GameObject implements DepletableWatcher, Animatable {
    
    public int count;
    private Depletable watchedItem;
    private AnimationBase animator;
        private final ArrayList<ActionListener> animationChangeListeners;

    public LifeHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
                this.animationChangeListeners = new ArrayList<>();

>>>>>>> dev
    }
    
    @Override
    public void Update() {
<<<<<<< HEAD
        this.count = this.watchedItem.getLifeValue();
    }
    
    @Override
    public Attackable getWatcher() {
=======
        this.count = this.watchedItem.getCount();
    }
    
    @Override
    public Depletable getWatcher() {
>>>>>>> dev
        return this.watchedItem;
    }

    @Override
<<<<<<< HEAD
    public void setWatcher(Attackable target) {
        this.watchedItem=target;
        this.count = this.watchedItem.getLifeValue();
        this.watchedItem.addAttackableListener(l -> Update());
=======
    public void setWatcher(Depletable target) {
        this.watchedItem=target;
        this.count = this.watchedItem.getCount();
        this.watchedItem.addDepletableListener(l -> Update());
>>>>>>> dev
    }

    @Override
    protected void GraphicsUpdateHandler() {
   
    }

    @Override
<<<<<<< HEAD
    protected void ClickHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void KeyHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
=======
>>>>>>> dev
    protected void UpdateEffectHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

<<<<<<< HEAD
    @Override
    public void setAnimator(AnimationBase animator) {
        this.animator = animator;
    }
=======
>>>>>>> dev

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
<<<<<<< HEAD
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
=======
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

>>>>>>> dev
}
