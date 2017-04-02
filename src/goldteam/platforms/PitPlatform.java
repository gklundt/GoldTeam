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
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.Platform;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 *
 * @author faaez
 */
public class PitPlatform extends GameObject implements Platform,
        Animatable,
        Collidable{

    private AnimationBase animator;
    private Polygon collider;
    private int width, height;
    private final HashMap<Collidable, CollisionPlane> colliders;
    
    public PitPlatform(GameEngine gamedata, Point initialPoint, int width, int height) {
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
        };
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
    }

    @Override
    protected void Update() {
 //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void GraphicsUpdateHandler() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public AnimationBase getAnimator() {
        return this.animator;
    }

    @Override
    public void addAnimationTimerListener(ActionListener listener) {
    }

    @Override
    public void addAnimationChangeListener(ActionListener listener) {
    }

    @Override
    public void removeAnimationChangeListener(ActionListener listener) {
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
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
    public void notifyCollidableListeners() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollided(boolean state) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCollided() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
