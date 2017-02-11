/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.Attackable;
import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.HudAnimationBase;
import java.awt.Point;

/**
 *
 * @author Caleb Dunham
 */
public class HeartHudItem extends GameObject implements AttackableWatcher, Animatable<HudAnimationBase> {
    
    public int count;
    private Attackable watchedItem;
    private HudAnimationBase animator;
    
    public HeartHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
    }
    
    @Override
    public void Update() {
        this.count = this.watchedItem.getHealthValue();
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
    public void setAnimator(HudAnimationBase animator) {
        this.animator = animator;
    }

    @Override
    public HudAnimationBase getAnimator() {
        return this.animator;
    }
}
