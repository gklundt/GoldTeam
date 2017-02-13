/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.characters;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
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
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author faaez
 */
public class StationaryGhost  extends GameObject implements 
        Attackable, /* Shield and Health accessors */
        Weapon, /* Adds damage to a movable object */
        Collidable, /* Information for Collision detection */
        Movable, /* Vectors and scalar for movement */
        Animatable<AnimationBase>, /* Getter/Setter for animator */ 
        Depletable,
        Controllable
        {
    
    private DoubleVector velocityVector;
    private AnimationBase animator;
    private final Integer initialVelocity;
    private Integer velocity;
    private final Random random;
    private Double health, shield;
    private ArrayList<ActionListener> attackableListeners;

    public StationaryGhost(GameEngine gameEngine, Point initialPoint) {
        super(gameEngine, initialPoint);
        this.random = new Random();
        this.initialVelocity = 20;
        this.velocity = this.initialVelocity;
        this.positionVector = initialPoint;
        DoubleVector rawVector = new DoubleVector(random.nextDouble() * 10, random.nextDouble() * 10);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        health = shield = 5.0;
        attackableListeners = new ArrayList<>();
    }

    @Override
    protected void Update() {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollider(Collidable obj, CollisionPlane direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCollider(Collidable obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Collidable, CollisionPlane> getColliders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return this.velocity;
    }

    @Override
    public Double getForce() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setForceDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DoubleVector getStrikeVector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStrikeVector(DoubleVector strikeVector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStrikeScalarDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAnimator(AnimationBase animator) {
        this.animator = animator;
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
    public void addAttackableListener(ActionListener listener) {
        this.attackableListeners.add(listener);
    }

    @Override
    public void addCollisionListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addWeaponListener(ActionListener listener) {}
    
    @Override
    public Integer getCount() {
        return this.getHealthValue();
    }

    @Override
    public void setCountDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDepletableListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for(ActionListener al : this.attackableListeners) {
            al.actionPerformed(e);
        }
    }

    @Override
    public void processKeyInput(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == 39){
            this.positionVector.x += initialVelocity;
        } else if(keyEvent.getKeyCode() == 37){
            this.positionVector.x -= initialVelocity;
        }
    }

    @Override
    public void processMouseInput(MouseEvent mouseEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
