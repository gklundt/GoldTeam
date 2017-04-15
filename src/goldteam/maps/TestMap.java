package goldteam.maps;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;
import java.awt.event.ActionListener;

public class TestMap extends GameObject implements Animatable {

    private AnimationBase animator;
    private final Point initialPoint;

    public TestMap(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.initialPoint = initialPoint;
        this.positionVector = this.initialPoint;
    }

// <editor-fold defaultstate="collapsed" desc="GameObject Implementation">
    @Override
    protected void Update() {
        this.positionVector = this.gamedata.getMapLocation();
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

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Animatable Implementation">

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
    public void removeAnimationChangeListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animationToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }
// </editor-fold>

}
