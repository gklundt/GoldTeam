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
public class CollectableArrows extends GameObject implements Animatable, Collidable, CollectableItem {

    private AnimationBase animator;
    private Polygon collider;
    private final HashMap<Collidable, CollisionPlane> colliders;
    private boolean state;
    private final Point initialPoint;

    public CollectableArrows(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        colliders = new HashMap<>();

        this.initialPoint = initialPoint.getLocation();
        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;
        this.positionVector.x = initialPoint.x + mapX;
        this.positionVector.y = initialPoint.y + mapY;

        collider = new Polygon();
        super.shape = collider;

        this.state = true;
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
        // does not change animation
    }

    @Override
    public void removeAnimationChangeListener(ActionListener listener) {
        // does not change animation
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        // does not change animation
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
        this.colliders.remove(obj);
    }

    @Override
    public HashMap<Collidable, CollisionPlane> getColliders() {
        return this.colliders;
    }

    @Override
    protected void Update() {
        int mapX = this.gamedata.getMapLocation().x;
        int mapY = this.gamedata.getMapLocation().y;
        this.positionVector.x = initialPoint.x + mapX;
        this.positionVector.y = initialPoint.y + mapY;
        if (this.collider != null) {
            this.collider.reset();
            this.collider.addPoint(this.positionVector.x, this.positionVector.y);
            this.collider.addPoint(this.positionVector.x + 50, this.positionVector.y);
            this.collider.addPoint(this.positionVector.x + 50, this.positionVector.y + 50);
            this.collider.addPoint(this.positionVector.x, this.positionVector.y + 50);
        }
    }

    @Override
    protected void GraphicsUpdateHandler() {
        this.Update();
    }

    @Override
    protected void UpdateEffectHandler() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undoCollider() {
        this.collider = null;
        this.removeMe = true;
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
}
