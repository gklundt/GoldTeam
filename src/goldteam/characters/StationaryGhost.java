/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.characters;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Attackable;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Controllable;
import goldteam.domain.Delta;
import goldteam.domain.Depletable;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Movable;
import goldteam.domain.VectorMath;
import goldteam.domain.Weapon;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author faaez
 */
public class StationaryGhost extends GameObject implements
        Attackable, /* Shield and Health accessors */
        Collidable, /* Information for Collision detection */
        Movable, /* Vectors and scalar for movement */
        Animatable /* Getter/Setter for animator */ {

    private final Random random;
    private boolean collidedLeft;

    private final Double initialVelocity;
    private final ArrayList<ActionListener> attackableListeners;
    private final ArrayList<ActionListener> depletableListeners;
    private final ArrayList<ActionListener> collidableListeners;
    private final ArrayList<ActionListener> animationChangeListeners;
    private final Double initialHealth;
    private final Double initialShield;
    private final Point initialPoint;

    private Double health;
    private Double shield;
    private int arrows;
    private DoubleVector velocityVector;
    private Double velocity;
    private final DoubleVector rawVector;

    private AnimationBase animator;
    private Polygon collider;
    private final HashMap<Collidable, CollisionPlane> colliders;
    private Integer depletableCount;
<<<<<<< HEAD
    
=======

    private boolean collided;
    private final HashMap<AnimationState, AnimationBase> animators;

>>>>>>> dev
    public StationaryGhost(GameEngine gameEngine, Point initialPoint) {
        super(gameEngine, initialPoint);

        this.random = new Random();

        this.initialPoint = initialPoint;
        this.initialVelocity = 20d;
        this.initialHealth = 5.0d;
        this.initialShield = 10.0d;

        this.positionVector = initialPoint;
        this.velocity = this.initialVelocity;
        this.rawVector = new DoubleVector(0d, 0d);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        this.depletableCount = 1;

        health = initialHealth;
        shield = initialShield;
        this.arrows = 10;
<<<<<<< HEAD
=======
        this.animators = new HashMap<>();
>>>>>>> dev

        attackableListeners = new ArrayList<>();
        depletableListeners = new ArrayList<>();
        collidableListeners = new ArrayList<>();
        animationChangeListeners = new ArrayList<>();

        int[] xPoly = {this.positionVector.x - 10,
            this.positionVector.x + 10,
            this.positionVector.x + 10,
            this.positionVector.x - 10
        };
        int[] yPoly = {this.positionVector.y - 10,
            this.positionVector.y - 10,
            this.positionVector.y + 10,
            this.positionVector.y + 10
        };
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
        colliders = new HashMap<>();
        this.collided = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Animatable Implementation">
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
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        ActionEvent e = new ActionEvent(animatorToRemove, 0, "");
        for (ActionListener al : this.animationChangeListeners) {
            al.actionPerformed(e);
        }
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        if (this.animators.isEmpty()) {
            this.animator = animator;
        }
        this.animators.put(state, animator);
    }

    @Override
    public AnimationBase getAnimator() {
        return this.animator;
    }

//</editor-fold>
    @Override
    protected void Update() {

        if (collidedLeft == false) {
            try {
                if (this.gamedata.getHeldKeys().isEmpty()) {
                    this.velocity = this.velocity > 0.5d ? this.velocity - 0.5d : 0;
                }

                this.positionVector.x += this.getVelocityVector().x;
                /*int deltaX = 0, deltaY = 0;
                deltaX += this.getVelocityVector().x;
                deltaY += this.getVelocityVector().y;*/
                //this.collider.translate(deltaX, deltaY);
                this.collider.reset();
                this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y - 10);
                this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y - 10);
                this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y + 10);
                this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y + 10);
                super.shape = collider;

            } catch (Exception e) {
            }
        }
    }

    @Override
    public int getShieldValue() {
        return this.shield.intValue();
    }

    @Override
    public int getHealthValue() {
        return this.health.intValue();
    }

    @Override
    public void setShieldDelta(Delta delta) {
        this.shield += delta.delta;
        this.notifyAttackableListeners();
    }

    @Override
    public void setHealthDelta(Delta delta) {
        this.health += delta.delta;
        this.notifyAttackableListeners();
    }

    @Override
    public Polygon getPolygon() {
        return this.collider;
    }

    @Override
    public void setCollider(Collidable obj, CollisionPlane direction) {
        colliders.put(obj, direction);
    }

    @Override
    public void removeCollider(Collidable obj) {
        colliders.remove(obj);
    }

    @Override
    public HashMap<Collidable, CollisionPlane> getColliders() {
        return this.colliders;
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
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
        /*if(this.velocityVector.x > 0){
            collidedLeft = true;
        }*/
        if (this.velocityVector.x > 0) {
            this.velocityVector.x *= -1d;
        } else if (this.velocityVector.x < 0) {
            this.velocityVector.x *= 1d;
        }
        this.velocity = 5d;
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
    public void addAttackableListener(ActionListener listener) {
        this.attackableListeners.add(listener);
    }

    @Override
<<<<<<< HEAD
    public void addWeaponListener(ActionListener listener) {
    }

    @Override
=======
>>>>>>> dev
    public void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.attackableListeners) {
            al.actionPerformed(e);
        }
    }
<<<<<<< HEAD
    
=======

>>>>>>> dev
    @Override
    public void notifyCollidableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener cl : this.collidableListeners) {
            cl.actionPerformed(e);
        }
    }


    @Override
    public void setCollided(boolean state) {
        this.collider = new Polygon();
        this.collided = state;
    }

    @Override
    public boolean isCollided() {
        return collided;
    }

    @Override
    public double getChargeValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChargeDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD

    @Override
    public int getArrowCount() {
        return arrows;
    }

    @Override
    public void setArrowDelta(Delta delta) {
        this.arrows += delta.delta.intValue();
    }

    @Override
    public int getLifeValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLifeValue(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Integer getCount() {
        return this.depletableCount;
    }

    @Override
    public void setCountDelta(Delta delta) {
        this.depletableCount += delta.delta.intValue();
        this.notifyDepletableListeners();
    }

    @Override
    public void addDepletableListener(ActionListener listener) {
        this.depletableListeners.add(listener);
    }

    @Override
    public void notifyDepletableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener dl : this.depletableListeners) {
            dl.actionPerformed(e);
        }
    }

=======
>>>>>>> dev
}
