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
import goldteam.domain.Delta;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Movable;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Joshua
 * @author Caleb Dunham
 */
public class Arrow extends GameObject implements Movable, Animatable, Collidable, Attackable {
    
    private int health;
    private AnimationBase animator;
    private DoubleVector velocityVector;
    private final Polygon collider;
    private Point prevPos;
    private Polygon initialCollider;
    private ArrayList<ActionListener> attackableListeners;
    private ArrayList<ActionListener> collidableListeners;
    private final HashMap<Collidable, CollisionPlane> colliders;
    

    public Arrow(GameEngine gamedata, Point initialPoint, DoubleVector velocityVector)
    {
        super(gamedata, initialPoint);
        attackableListeners = new ArrayList<>();
        collidableListeners = new ArrayList<>();
        colliders = new HashMap<>();
        
        init();
        
        health = 1;
        collider = initialCollider;
        super.shape= initialCollider;
        this.velocityVector = velocityVector;
        prevPos = (Point)(initialPoint.clone());
    }
    
    private void init() {
        int [] xPoly = {this.positionVector.x - 10, 
                        this.positionVector.x + 10, 
                        this.positionVector.x + 10,
                        this.positionVector.x - 10
        };
        int [] yPoly = {this.positionVector.y - 10, 
                        this.positionVector.y - 10,
                        this.positionVector.y + 10,
                        this.positionVector.y + 10
        };
        initialCollider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
        
    }

    @Override
    protected void Update()
    {
        velocityVector.y += 1.5;  //Gravity
        this.positionVector.x += this.getVelocityVector().x;
        this.positionVector.y += this.getVelocityVector().y;
        
        this.positionVector.x += this.getVelocityVector().x;
        
        DoubleVector d = new DoubleVector((double)(positionVector.x - prevPos.x), (double)(positionVector.y - prevPos.y));
        double currentAngle = Math.atan(d.y/d.x);
        animator.af.setToRotation(currentAngle);
        
        this.collider.reset();
        this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y - 5);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y - 5);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y + 5);
        this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y + 5);
        super.shape = collider;
    }

    @Override
    protected void GraphicsUpdateHandler()
    {
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
    public DoubleVector getVelocityVector()
    {
        return velocityVector;
    }

    @Override
    public void setVelocityScalarDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
        this.velocityVector.x *= xDelta.delta;
        this.velocityVector.y *= yDelta.delta;
    }

    @Override
    public Integer getVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAnimator(AnimationBase animator)
    {
        this.animator = animator;
    }

    @Override
    public AnimationBase getAnimator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAnimationTimerListener(ActionListener listener)
    {
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
    public int getShieldValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHealthValue() {
        return health;
    }

    @Override
    public void setShieldDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHealthDelta(Delta delta) {
        this.health += delta.delta;
        this.notifyAttackableListeners();
    }
    
    @Override
    public void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.attackableListeners) {
            al.actionPerformed(e);
        }
    }
    
    @Override
    public void notifyCollidableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener cl : this.collidableListeners) {
            cl.actionPerformed(e);
        }
    }

    @Override
    public void addAttackableListener(ActionListener listener) {
        this.attackableListeners.add(listener);
    }

    @Override
    public int getArrowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setArrowDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLifeValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLifeValue(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNewAnimator(String string) {
        this.animator = null;
    }
    
}
