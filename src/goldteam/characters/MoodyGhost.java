/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.characters;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Controllable;
import goldteam.domain.Delta;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Movable;
import goldteam.domain.VectorMath;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author faaez
 */
public class MoodyGhost extends GameObject implements
        Movable, /* Vectors and scalar for movement */
        Animatable, /* Getter/Setter for animator */
        Controllable {

    private final Double initialVelocity;
    private final Double initialHealth;
    private final Double initialShield;
    private final Point initialPoint;

    private DoubleVector velocityVector;
    private Double velocity;
    private final DoubleVector rawVector;
    private AnimationBase animator;
    private final HashMap<AnimationState, AnimationBase> animators;
    private final ArrayList<ActionListener> animationChangeListeners;
    private AnimationBase removeAnimator;

    public MoodyGhost(GameEngine gameEngine, Point initialPoint) {
        super(gameEngine, initialPoint);
        this.animationChangeListeners = new ArrayList<>();
        this.animators = new HashMap<>();

        this.initialPoint = initialPoint;
        this.initialVelocity = 10d;
        this.initialHealth = 5.0d;
        this.initialShield = 10.0d;

        this.positionVector = initialPoint;
        this.velocity = this.initialVelocity;
        this.rawVector = new DoubleVector(0d, 0d);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());

    }

    @Override
    protected void Update() {

        if (animator == null | removeAnimator == null) {
            return;
        }

        try {
            if (this.gamedata.getHeldKeys().isEmpty()) {
                this.velocity = this.velocity > 0.5d ? this.velocity - 0.5d : 0;
            }
            Double testX = this.positionVector.x + this.getVelocityVector().x;
            Double testY = this.positionVector.y + this.getVelocityVector().y;
            if ((testX > 20) && (testX < (this.gamedata.getVisibleDimensions().width - 20))) {
                this.positionVector.x += this.getVelocityVector().x;

            } else {
                this.velocity = 0d;
            }
            if ((testY > 20) && (testY < (this.gamedata.getVisibleDimensions().height - 20))) {
                this.positionVector.y += this.getVelocityVector().y;
            } else {
                this.velocity = 0d;
            }
        } catch (Exception e) {
        }

        if (this.velocity == 0d) {
            this.removeAnimator = animator;
            this.animator = this.animators.get(AnimationState.DEFAULT);
            this.notifyAnimationChangeListeners(removeAnimator);
        }

        if (this.velocity > 0d && this.velocity <= 12.0) {
            this.removeAnimator = animator;
            this.animator = this.animators.get(AnimationState.WALKING_LEFT);
            this.notifyAnimationChangeListeners(removeAnimator);
        }

        if (this.velocity > 12d) {
            this.removeAnimator = animator;
            this.animator = this.animators.get(AnimationState.JUMPING_LEFT);
            this.notifyAnimationChangeListeners(removeAnimator);
        }

    }

    @Override
    public DoubleVector getVelocityVector() {
        return VectorMath.getVelocityVector(this.velocityVector, this.velocity.doubleValue());
    }

    @Override
    public void setVelocityScalarDelta(Delta delta) {
        this.velocity += delta.delta.intValue();
    }

    @Override
    public Integer getVelocity() {
        return this.velocity.intValue();
    }

    @Override
    public AnimationBase getAnimator() {
        return this.animator;
    }

    @Override
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void GraphicsUpdateHandler() {
        Update();
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
    public void processKeyInput() {

        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_D)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.x += this.velocity;
        }
        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_A)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.x -= this.velocity;
        }
        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_W)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.y -= this.velocity;
        }
        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_S)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.y += this.velocity;
        }
        this.velocityVector = VectorMath.getVelocityVector(rawVector, velocity);
    }

    @Override
    public void processMouseInput() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void addAnimator(AnimationState state, AnimationBase animator) {
        if (animators.isEmpty()) {
            this.animator = animator;
            this.removeAnimator = animator;
        }
        this.animators.put(state, animator);

    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        ActionEvent e = new ActionEvent(animatorToRemove, 0, null);
        for (ActionListener al : this.animationChangeListeners) {
            al.actionPerformed(e);
        }
    }

}
