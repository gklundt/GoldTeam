/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.platforms;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionPlane;
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Platform;
=======
import goldteam.domain.Delta;
import goldteam.domain.Doors;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Movable;
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 *
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
 * @author faaez
 */
public class LavaPlatform extends GameObject implements Platform, Animatable, Collidable{
    
    private AnimationBase animator;
    private int width, height;
    private Polygon collider;
    private final HashMap<Collidable, CollisionPlane> colliders;
    
    public LavaPlatform(GameEngine gamedata, Point initialPoint, int width, int height) {
        super(gamedata, initialPoint);
        this.width = width;
        this.height = height;
        
        colliders = new HashMap<>();
        
        int [] xPoly = {this.positionVector.x, 
                        this.positionVector.x + this.width, 
                        this.positionVector.x + this.width,
                        this.positionVector.x
        };
        int [] yPoly = {this.positionVector.y, 
                        this.positionVector.y,
                        this.positionVector.y + this.height,
                        this.positionVector.y + this.height
=======
 * @author Mishal
 */
public class DoorsPlatform extends GameObject implements Doors, Movable, Animatable, Collidable {

    private AnimationBase animator;
    private Polygon collider;
    private int width, height;
    private final HashMap<Collidable, CollisionPlane> colliders;

    public DoorsPlatform(GameEngine gamedata, Point initialPoint, int width, int height) {
        super(gamedata, initialPoint);
        this.width = width;
        this.height = height;

        colliders = new HashMap<>();

        int[] xPoly = {this.positionVector.x,
            this.positionVector.x + this.width,
            this.positionVector.x + this.width,
            this.positionVector.x
        };
        int[] yPoly = {this.positionVector.y,
            this.positionVector.y,
            this.positionVector.y + this.height,
            this.positionVector.y + this.height
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
        };
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
    }

    @Override
    protected void Update() {
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
    }

    @Override
    protected void GraphicsUpdateHandler() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
    protected void ClickHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void KeyHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
=======
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
    protected void UpdateEffectHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getDurabilityPercentage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDurabilityPercentage(Double durabilityPercentage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getDecayRate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDecayRate(Double decayRate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSolidTop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSolidBottom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSolidTop(Boolean isSolidTop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSolidBottom(Boolean isSolidBottom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
    public void setAnimator(AnimationBase animator) {
        this.animator = animator;
    }

    @Override
=======
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
    public AnimationBase getAnimator() {
        return this.animator;
    }

    @Override
    public void addAnimationTimerListener(ActionListener listener) {
        this.gamedata.addAnimationUpdateTimerListener(listener);
    }

    @Override
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
    public void addAnimationChangeListener(ActionListener listener) {
=======
    public DoubleVector getVelocityVector() {
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
    public void notifyAnimationChangeListeners() {
=======
    public void setVelocityScalarDelta(Delta delta) {
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
    public void addAnimator(AnimationState state, AnimationBase animator) {
=======
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
    public AnimationBase getRemoveAnimator() {
=======
    public Integer getVelocity() {
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
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
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
        return this.colliders;
=======
        return colliders;
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
>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
    }

    @Override
    public void notifyCollidableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD:src/goldteam/platforms/LavaPlatform.java
    
=======

    @Override
    public void setCollided(boolean state) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCollided() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

>>>>>>> dev:src/goldteam/platforms/DoorsPlatform.java
}
