package goldteam.characters;

import goldteam.domain.*;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * ArcherMan Proposal. Extends a game object and implements behavior interfaces
 * applicable to a Controllable Character.
 *
 * @author gordon
 */
public class MainCharacterGhost extends GameObject implements
        Attackable, /* Shield and Health accessors */
        Weapon, /* Adds damage to a movable object */
        Collidable, /* Information for Collision detection */
        Movable, /* Vectors and scalar for movement */
        Animatable, /* Getter/Setter for animator */
        Depletable {

    private final Random random;

    private final Integer initialVelocity;
    private final ArrayList<ActionListener> attackableListeners;
    private final Double initialHealth;
    private final Double initialShield;
    private final Point initialPoint;

    private Double health;
    private Double shield;
    private DoubleVector velocityVector;
    private Integer velocity;
    private DoubleVector rawVector;

    private AnimationBase animator;
    private final Polygon collider;
    private final HashMap<Collidable, CollisionPlane> currentColliders;

    public MainCharacterGhost(GameEngine gameEngine, Point initialPoint) {
        super(gameEngine, initialPoint);
        this.currentColliders = new HashMap<>();
        this.random = new Random();

        this.initialPoint = initialPoint;
        this.initialVelocity = 10;
        this.initialHealth = 20.0d;
        this.initialShield = 20.0d;

        this.positionVector = this.initialPoint;
        this.velocity = this.initialVelocity;
        this.rawVector = new DoubleVector(random.nextDouble() * 10, random.nextDouble() * 10);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());

        this.health = this.initialHealth;
        this.shield = this.initialShield;

        attackableListeners = new ArrayList<>();

        int[] xPoly = {this.positionVector.x - 12,
            this.positionVector.x + 12,
            this.positionVector.x + 12,
            this.positionVector.x - 12
        };
        int[] yPoly = {this.positionVector.y - 12,
            this.positionVector.y - 12,
            this.positionVector.y + 12,
            this.positionVector.y + 12
        };
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
    }

    @Override
    protected void Update() {

        if (this.positionVector == null || this.animator == null) {
            return;
        }
        Double dx = random.nextDouble() * 100;
        Double dy = random.nextDouble() * 100;

        if (this.positionVector.x >= this.animator.getWidth() - 50) {
            rawVector.x = -1 * dx;
            rawVector.y = dy;
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }
        if (this.positionVector.y >= this.animator.getHeight() - 50) {
            rawVector.x = dx;
            rawVector.y = -1 * dy;
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }
        if (this.positionVector.x <= 50) {
            rawVector.x = dx;
            rawVector.y = -1 * dy;
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }
        if (this.positionVector.y <= 50) {
            rawVector.x = dx;
            rawVector.y = 1 + dy;
            this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        }

        this.positionVector.x += this.getVelocityVector().x;
        this.positionVector.y += this.getVelocityVector().y;

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
        if (!this.currentColliders.containsKey(obj)) {
            this.currentColliders.put(obj, direction);
        }
    }

    @Override
    public void removeCollider(Collidable obj) {
        if (this.currentColliders.containsKey(obj)) {
            this.currentColliders.remove(obj);
        }
    }

    @Override
    public HashMap<Collidable, CollisionPlane> getColliders() {
        return currentColliders;
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
    public AnimationBase getAnimator() {
        return this.animator;
    }

    @Override
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
        this.velocityVector.x = xDelta.delta;
        this.velocityVector.y = yDelta.delta;
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
    public void addWeaponListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    @Override
    public void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.attackableListeners) {
            al.actionPerformed(e);
        }
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
    public void addAnimator(AnimationState state, AnimationBase animator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyDepletableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyCollidableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getChargeValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChargeDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollided(boolean state) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCollided() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public void notifyWeaponListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChargeValue(double chargeValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
