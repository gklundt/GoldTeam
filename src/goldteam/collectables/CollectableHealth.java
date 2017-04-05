/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.collectables;

import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.CollectableItem;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionPlane;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 *
 * @author faaez
 */
<<<<<<< HEAD:src/goldteam/collectables/Shields.java
public class Shields extends GameObject implements Animatable, Collidable, CollectableItem{
    
=======
public class CollectableHealth extends GameObject implements Animatable, Collidable, CollectableItem {

>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
    private AnimationBase animator;
    private Polygon collider;
    private final HashMap<Collidable, CollisionPlane> colliders;
    private boolean state;

<<<<<<< HEAD:src/goldteam/collectables/Shields.java
    public Shields(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        
        colliders = new HashMap<>();
        
        int [] xPoly = {this.positionVector.x, 
                        this.positionVector.x + 50, 
                        this.positionVector.x + 50,
                        this.positionVector.x
        };
        int [] yPoly = {this.positionVector.y, 
                        this.positionVector.y,
                        this.positionVector.y + 50,
                        this.positionVector.y + 50
        };
        
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
        
=======
    public CollectableHealth(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        
        colliders = new HashMap<>();

        int[] xPoly = {this.positionVector.x,
            this.positionVector.x + 50,
            this.positionVector.x + 50,
            this.positionVector.x
        };
        int[] yPoly = {this.positionVector.y,
            this.positionVector.y,
            this.positionVector.y + 50,
            this.positionVector.y + 50
        };

        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;

>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
        this.state = true;
    }

    @Override
    protected void Update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void GraphicsUpdateHandler() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/collectables/Shields.java
    protected void ClickHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void KeyHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
=======
>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
    protected void UpdateEffectHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/collectables/Shields.java
    public void setAnimator(AnimationBase animator) {
        this.animator = animator;
    }

    @Override
=======
>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
    public AnimationBase getAnimator() {
        return this.animator;
    }

    @Override
    public void addAnimationTimerListener(ActionListener listener) {
        this.gamedata.addAnimationUpdateTimerListener(listener);
    }

    @Override
    public void addAnimationChangeListener(ActionListener listener) {
<<<<<<< HEAD:src/goldteam/collectables/Shields.java
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyAnimationChangeListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
=======
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAnimationChangeListener(ActionListener listener) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
<<<<<<< HEAD:src/goldteam/collectables/Shields.java
    public AnimationBase getRemoveAnimator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
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
        this.colliders.remove(obj);
    }

    @Override
    public HashMap<Collidable, CollisionPlane> getColliders() {
        return this.colliders;
    }
    
    @Override
<<<<<<< HEAD:src/goldteam/collectables/Shields.java
        public void setState(boolean state){
        this.state = state;
    }
    
    @Override
    public boolean getState(){
        return this.state;
    }
    
    @Override
    public void undoCollider(){
        this.collider = new Polygon();
=======
    public void undoCollider() {
        this.collider = null;
        this.removeMe = true;
>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
    }

    @Override
    public void notifyCollidableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollided(boolean state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCollided() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD:src/goldteam/collectables/Shields.java
    
=======

>>>>>>> e86910b73629133ea96f3d82a236f565a5a4f13d:src/goldteam/collectables/CollectableHealth.java
}
