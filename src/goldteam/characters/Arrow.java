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
import goldteam.domain.VectorMath;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Arrow
        extends GameObject
        implements Movable, Animatable, Collidable, Attackable {

//<editor-fold defaultstate="collapsed" desc="Private Declarations">
    private int health;
    private AnimationBase animator;
    private DoubleVector velocityVector;
    private Point prevPos;
    private Polygon collider;
    private Polygon initialCollider;
    private final ArrayList<ActionListener> attackableListeners;
    private final ArrayList<ActionListener> collidableListeners;
    private final HashMap<Collidable, CollisionPlane> colliders;
    private boolean collided;
    public final ArrayList<ActionListener> animationChangeListeners;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Arrow(GameEngine gamedata, Point initialPoint, DoubleVector velocityVector) {
        super(gamedata, initialPoint);
        attackableListeners = new ArrayList<>();
        collidableListeners = new ArrayList<>();
        colliders = new HashMap<>();

        init();

        health = 1;
        collider = initialCollider;
        super.shape = initialCollider;
        this.velocityVector = velocityVector;
        collided = false;
        prevPos = (Point)(initialPoint.clone());
        this.animationChangeListeners = new ArrayList<>();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Initialization Routine">
    private void init() {
        int[] xPoly = {this.positionVector.x - 15,
            this.positionVector.x + 15,
            this.positionVector.x + 15,
            this.positionVector.x - 15
        };
        int[] yPoly = {this.positionVector.y - 5,
            this.positionVector.y - 5,
            this.positionVector.y + 2,
            this.positionVector.y + 2
        };
        initialCollider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GameObject Implementation">
    @Override
    protected void Update() {
        if (removeMe) {
            this.remove();
            return;
        }
        
        double d = VectorMath.getMagnitude(this.gamedata.getMovableCharacter().PositionVector(), this.positionVector);
        if (d > 300) {
            this.remove();
        }
        velocityVector.y += 1.5;  //Gravity
        this.positionVector.x += this.getVelocityVector().x;
        this.positionVector.y += this.getVelocityVector().y;

        DoubleVector dangle = new DoubleVector((double)(positionVector.x - prevPos.x), (double)(positionVector.y - prevPos.y));
        double currentAngle = Math.atan(dangle.y/dangle.x);
        animator.af.setToRotation(currentAngle);
        
        this.collider.reset();
        this.collider.addPoint(this.positionVector.x - 15, this.positionVector.y - 5);
        this.collider.addPoint(this.positionVector.x + 15, this.positionVector.y - 5);
        this.collider.addPoint(this.positionVector.x + 15, this.positionVector.y + 2);
        this.collider.addPoint(this.positionVector.x - 15, this.positionVector.y + 2);
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

//<editor-fold defaultstate="collapsed" desc="Movable Implementation">
    @Override
    public DoubleVector getVelocityVector() {
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
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animator = animator;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Collidable Implementation">
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
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener cl : this.collidableListeners) {
            cl.actionPerformed(e);
        }
    }

    @Override
    public void setCollided(boolean state) {
        this.collider = new Polygon();
        this.collided = state;
    }

    @Override
    public boolean isCollided() {
        return this.collided;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Attackable Implementation">
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
    public void addAttackableListener(ActionListener listener) {
        this.attackableListeners.add(listener);
    }

//</editor-fold>
}
