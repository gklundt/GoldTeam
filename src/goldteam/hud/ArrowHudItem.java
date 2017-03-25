
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
public class ArrowHudItem extends GameObject implements DepletableWatcher, Animatable {
    
    public int count;
    private Depletable watchedItem;
    private AnimationBase animator;
    
    public ArrowHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
    }
    
    @Override
    public void Update() {
        //this.count = this.watchedItem.getArrowCount();
    }
    
    @Override
    public Depletable getWatcher() {
        return this.watchedItem;
    }

    @Override
    public void setWatcher(Depletable target) {
        this.watchedItem = target;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
