/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Weapon;
import goldteam.domain.WeaponWatcher;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Caleb Dunham
 */
public class ArrowChargeIndicator extends GameObject implements
        Animatable, /* Will be drawn on screen */
        WeaponWatcher /* Watches the archer */ {

    private double currentCharge;
    private Weapon watchedItem;
    private AnimationBase animator;
    private final ArrayList<Object> animationChangeListeners;

    public ArrowChargeIndicator(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.animationChangeListeners = new ArrayList<>();

    }

    @Override
    protected void Update() {
        this.currentCharge = this.watchedItem.getChargeValue();
    }

    @Override
    protected void GraphicsUpdateHandler() {
        this.Update();

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
    public void notifyAnimationChangeListeners(AnimationBase animationToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }

    @Override
    public void setWatcher(Weapon target) {
        this.watchedItem = target;
        this.currentCharge = this.watchedItem.getChargeValue();
        this.watchedItem.addWeaponListener(l -> Update());
    }

    @Override
    public Weapon getWatcher() {
        return this.watchedItem;
    }

}
