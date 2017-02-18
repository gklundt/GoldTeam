package goldteam.maps;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Delta;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Movable;
import goldteam.domain.VectorMath;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

public class TestMap extends GameObject implements Animatable, Movable {

    private AnimationBase animator;
    private final double initialVelocity;
    private DoubleVector velocityVector;
    private final Point initialPoint;

    public TestMap(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.initialPoint = initialPoint;
        this.positionVector = this.initialPoint;
        this.initialVelocity = 0.0d;
        this.velocityVector = VectorMath.getUnitVector(initialPoint, initialPoint);

    }

// <editor-fold defaultstate="collapsed" desc="GameObject Implementation">
    @Override
    protected void Update() {

        // Animator dimensions
        Integer aw = this.animator.getWidth();
        Integer ah = this.animator.getHeight();

        // Position of map
        Integer px = this.positionVector.x;
        Integer py = this.positionVector.y;

        // Proposed Location
        Double ppx = this.positionVector.x + this.velocityVector.x;
        Double ppy = this.positionVector.y + this.velocityVector.y;

        if (ppx >= -1 * (aw - 800) && ppx <= 0) {
            this.positionVector.x = ppx.intValue();
        } else {
            this.velocityVector.x = 0.0d;
        }
        if (ppy >= -1 * (ah - 600) && ppy <= 0) {
            this.positionVector.y = ppy.intValue();
        } else {
            this.velocityVector.y = 0.0d;
        }

    }

    @Override
    protected void GraphicsUpdateHandler() {
        this.Update();
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

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Animatable Implementation">
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
// </editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movable Implementation">
    @Override
    public DoubleVector getVelocityVector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocityScalarDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
        this.velocityVector.x += xDelta.delta;
        this.velocityVector.y += yDelta.delta;
        //this.velocityVector = VectorMath.getUnitVector(this.velocityVector);
    }

    @Override
    public Integer getVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>
}
