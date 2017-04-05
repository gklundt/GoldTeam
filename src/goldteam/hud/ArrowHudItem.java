<<<<<<< HEAD

=======
>>>>>>> dev
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
public class ArrowHudItem extends GameObject implements AttackableWatcher, Animatable {
    
    public int count;
    private Attackable watchedItem;
    private AnimationBase animator;
    
    public ArrowHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
    }
    
    @Override
    public void Update() {
        this.count = this.watchedItem.getArrowCount();
    }
    
    @Override
    public Attackable getWatcher() {
=======
public class ArrowHudItem extends GameObject implements DepletableWatcher, Animatable {

    public int count;
    private Depletable watchedItem;
    private AnimationBase animator;
    private final ArrayList<ActionListener> animationChangeListeners;

    public ArrowHudItem(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.animationChangeListeners = new ArrayList<>();

    }

    @Override
    public void Update() {
        //this.count = this.watchedItem.getArrowCount();
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
        this.count = this.watchedItem.getHealthValue();
        this.watchedItem.addAttackableListener(l -> Update());
=======
    public void setWatcher(Depletable target) {
        this.watchedItem = target;
        this.count = this.watchedItem.getCount();
        this.watchedItem.addDepletableListener(l -> Update());
>>>>>>> dev
    }

    @Override
    protected void GraphicsUpdateHandler() {
<<<<<<< HEAD
   
    }

    @Override
    protected void ClickHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void KeyHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======

>>>>>>> dev
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
<<<<<<< HEAD
    public void setAnimator(AnimationBase animator) {
        this.animator = animator;
    }

    @Override
=======
>>>>>>> dev
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
>>>>>>> dev
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
