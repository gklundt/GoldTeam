package goldteam.characters;

import goldteam.domain.*;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * ArcherMan Proposal. Extends a game object and implements behavior interfaces
 * applicable to a Controllable Character.
 *
 * @author gordon
 */
public class Ghost extends GameObject implements
        Attackable, /* Shield and Health accessors */
        Weapon, /* Adds damage to a movable object */
        Collidable, /* Information for Collision detection */
        Movable, /* Vectors and scalar for movement */
        Animatable /* Getter/Setter for animator */ {

    private DoubleVector velocityVector;
    private AnimationBase animator;
    private final Integer initialVelocity;
    private Integer velocity;
    private final Random random;

    public Ghost() {
        this.random = new Random();
        this.initialVelocity = 20;
        this.velocity = this.initialVelocity;
        this.positionVector = new Point(100, 80);
        DoubleVector rawVector = new DoubleVector(random.nextDouble() * 10, random.nextDouble() * 10);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
    }

    @Override
    public void Update() {
        Double dx = random.nextDouble() * 10;
        Double dy = random.nextDouble() * 10;

        if (this.positionVector.x >= this.animator.getWidth() - 50) {
            DoubleVector rawVector = new DoubleVector(-1 * dx, dy);
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }
        if (this.positionVector.y >= this.animator.getHeight() - 50) {
            DoubleVector rawVector = new DoubleVector(dx, -1 * dy);
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }
        if (this.positionVector.x <= 50) {
            DoubleVector rawVector = new DoubleVector(dx, dy);
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }
        if (this.positionVector.y <= 50) {
            DoubleVector rawVector = new DoubleVector(dx, dy);
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }

        this.positionVector.x += this.getVelocityVector().x;
        this.positionVector.y += this.getVelocityVector().y;
    }

    @Override
    public Double getShieldValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getHealthValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setShieldDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHealthDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Polygon getPolygon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollider(GameObject obj, CollisionPlane direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCollider(GameObject obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GameObject> getColliders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DoubleVector getVelocityVector() {
        return this.velocityVector;
    }

    @Override
    public void setVelocityScalarDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
