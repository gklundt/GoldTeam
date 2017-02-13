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
public class Ghost extends GameObject implements
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

    public Ghost(GameEngine gameEngine, Point initialPoint) {
        super(gameEngine, initialPoint);
        this.random = new Random();

        this.initialPoint = initialPoint;
        this.initialVelocity = 20;
        this.initialHealth = 20.0d;
        this.initialShield = 20.0d;

        this.positionVector = this.initialPoint;
        this.velocity = this.initialVelocity;
        this.rawVector = new DoubleVector(random.nextDouble() * 10, random.nextDouble() * 10);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        
        this.health = this.initialHealth; 
        this.shield = this.initialShield;

        attackableListeners = new ArrayList<>();
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

    private void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.attackableListeners) {
            al.actionPerformed(e);
        }
    }

    @Override
    public void addAnimationTimerListener(ActionListener listener) {
        this.gamedata.addAnimationUpdateTimerListener(listener);
    }
}
