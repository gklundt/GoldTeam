package goldteam.characters;

import goldteam.domain.*;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Ghost
        extends GameObject
        implements Attackable, /* Shield and Health accessors */
        Weapon, /* Adds damage to a movable object */
        Collidable, /* Information for Collision detection */
        Movable, /* Vectors and scalar for movement */
        Animatable, /* Getter/Setter for animator */
        Depletable,
        Enemy {

    private final Random random;
    private final Integer initialVelocity;
    private final ArrayList<ActionListener> attackableListeners;
    private final Double initialHealth;
    private final Double initialShield;
    private final Point initialPoint;

    private Double health;
    private Double shield;
    private int arrows;
    private DoubleVector velocityVector;
    private Integer velocity;
    private final DoubleVector rawVector;

    private AnimationBase animator;
    private final HashMap<AnimationState, AnimationBase> animators;
    private final ArrayList<ActionListener> animationChangeListeners;
    private AnimationBase removeAnimator;
    private final Polygon collider;
    private final HashMap<Collidable, CollisionPlane> currentColliders;
    private Iterable<ActionListener> animationListeners;
<<<<<<< HEAD
=======
    private boolean collided;
>>>>>>> dev

    public Ghost(GameEngine gameEngine, Point initialPoint) {
        super(gameEngine, initialPoint);
        this.currentColliders = new HashMap<>();
        this.random = new Random();

        this.initialPoint = initialPoint;
        this.initialVelocity = 10;
<<<<<<< HEAD
        this.initialHealth = 3.0d;
=======
        this.initialHealth = 6.0d;
>>>>>>> dev
        this.initialShield = 0.0d;

        this.positionVector = this.initialPoint;
        this.velocity = this.initialVelocity;
        this.rawVector = new DoubleVector(random.nextDouble() * 10, random.nextDouble() * 10);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());

        this.health = this.initialHealth;
        this.shield = this.initialShield;
<<<<<<< HEAD
        this.arrows = 20;    
=======
>>>>>>> dev
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
<<<<<<< HEAD
        
        this.animators = new HashMap<>();
        this.animationChangeListeners = new ArrayList<>();
        
=======

        this.animators = new HashMap<>();
        this.animationChangeListeners = new ArrayList<>();

>>>>>>> dev
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
        collided = false;
    }

//<editor-fold defaultstate="collapsed" desc="GameObject Implementation">
    @Override
    protected void Update() {

        if (this.health <= 0) {
            this.remove();
        }

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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Attackable Implementation">
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
        if (this.animators.get(AnimationState.HURT) == null) {
            this.removeAnimator = this.animator;
            this.animator = this.animators.get(AnimationState.HURT);
            this.notifyAnimationChangeListeners(removeAnimator);
        }
        this.notifyAttackableListeners();
    }

    @Override
    public void setHealthDelta(Delta delta) {
        
        if (this.animators.get(AnimationState.HURT) != null) {
            this.removeAnimator = this.animator;
            this.animator = this.animators.get(AnimationState.HURT);
            this.notifyAnimationChangeListeners(removeAnimator);
        }
        this.health += delta.delta;
        this.notifyAttackableListeners();
    }

    @Override
    public void addAttackableListener(ActionListener listener) {
        this.attackableListeners.add(listener);
    }

    @Override
    public void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.attackableListeners) {
            al.actionPerformed(e);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Weapon Implementation">
    @Override
    public double getChargeValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChargeDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyWeaponListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChargeValue(double chargeValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addWeaponListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Collidable Implementation">
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
<<<<<<< HEAD
    public AnimationBase getRemoveAnimator() {
        return this.removeAnimator;
    }
    
    @Override
    public void setAnimator(AnimationBase animator) {
        this.animators.put(AnimationState.DEFAULT, animator);
        this.animator = animator;
=======
    public HashMap<Collidable, CollisionPlane> getColliders() {
        return currentColliders;
>>>>>>> dev
    }

    @Override
    public void notifyCollidableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollided(boolean state) {
        this.collided = state;
    }

    @Override
    public boolean isCollided() {
        return this.collided;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movable Implementation">
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
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta) {
        this.velocityVector.x = xDelta.delta;
        this.velocityVector.y = yDelta.delta;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Animatable Implementation">
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
        this.animationChangeListeners.add(listener);
    }

    @Override
    public void removeAnimationChangeListener(ActionListener listener) {
        this.animationChangeListeners.remove(listener);
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        if (this.animators.isEmpty()) {
            this.animator = animator;
        }
        this.animators.put(state, animator);
    }

    @Override
<<<<<<< HEAD
    public void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.attackableListeners) {
=======
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        ActionEvent e = new ActionEvent(removeAnimator, 0, null);
        for (ActionListener al : this.animationChangeListeners) {
>>>>>>> dev
            al.actionPerformed(e);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Depletable Implementation">
    @Override
    public Integer getCount() {
        return this.getHealthValue();
    }

    @Override
<<<<<<< HEAD
    public void addAnimationChangeListener(ActionListener listener) {
        this.animationChangeListeners.add(listener);
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animators.put(state, animator);
    }

    @Override
    public void notifyAnimationChangeListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
//        for (ActionListener al : this.animationListeners) {
//            al.actionPerformed(e);
//        }
        for (ActionListener al : this.animationChangeListeners) {
            al.actionPerformed(e);
        }
    }

    @Override//TEMPORARY
    public int getArrowCount() {
        return arrows;
    }

    @Override//TEMPORARY
    public void setArrowDelta(Delta delta) {
        this.arrows = delta.delta.intValue();
    }

    @Override
    public int getLifeValue() {
=======
    public void setCountDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDepletableListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyDepletableListeners() {
>>>>>>> dev
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

<<<<<<< HEAD
    @Override
    public void setLifeValue(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNewAnimator(AnimationState as) {
        this.animator = this.animators.get(as);
    }

    public void setRemoveAnimator(AnimationBase animator) {
        this.removeAnimator = animator;
    }

    @Override
    public void notifyDepletableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyCollidableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
=======
//    private void setNewAnimator(AnimationState as) {
//        this.animator = this.animators.get(as);
//    }
//
//    private void setRemoveAnimator(AnimationBase animator) {
//        this.removeAnimator = animator;
//    }
>>>>>>> dev
}
