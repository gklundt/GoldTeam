package goldteam.characters;

import goldteam.domain.*;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class ArcherMan extends GameObject
        implements Attackable, /* Shield and Health accessors */
        Animatable, /* Animations */
        Archer, /* Strength accessor (from Fighter) and ready/aim/fire routines */
        Collidable, /* Information for Collision detection */
        Controllable, /* Process Keyboard and Mouse events */
        Movable, /* Vectors and scalar for movement */
        Spawnable, /* Respawn details */
        Depletable /* Life Counter */ {

    private int lives;

    private boolean right;
    private boolean left;
    private boolean jump;
    private boolean canDoubleJump;
    private Boolean isFacingLeft = false;

    private final Double initialHealth;
    private final Double initialShield;
    private final Double initialVelocity;
    private final Point initialPoint;
    private final DoubleVector rawVector;
    private final double maxVelocity;

    private int health;
    private int shield;

    private final ArrayList<ActionListener> animationChangeListeners;
    private final ArrayList<ActionListener> attackableListeners;
    private final ArrayList<ActionListener> depletableListeners;
    private final HashMap<AnimationState, AnimationBase> animators;
    private final HashMap<Collidable, CollisionPlane> colliders;

    private DoubleVector velocityVector;
    private double velocity;
    private AnimationBase animator;
    private AnimationBase removeAnimator;
    private Polygon collider;

    public ArcherMan(GameEngine gameData, Point initialPoint) {

        super(gameData, initialPoint);
        this.lives = 3;
        this.initialPoint = initialPoint;
        this.positionVector = initialPoint;
        this.animators = new HashMap<>();
        this.rawVector = new DoubleVector(0d, 0d);
        this.velocityVector = new DoubleVector();
        this.attackableListeners = new ArrayList<>();
        this.depletableListeners = new ArrayList<>();
        this.animationChangeListeners = new ArrayList<>();
        this.canDoubleJump = true;
        this.initialVelocity = 10d;
        this.initialHealth = 5.0d;
        this.initialShield = 10.0d;
        this.maxVelocity = 10d;

        this.health = initialHealth.intValue();
        this.shield = initialShield.intValue();
        this.velocity = initialVelocity;
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity);
        this.shape = new Polygon();

        int[] xPoly = {this.positionVector.x - 10,
            this.positionVector.x + 10,
            this.positionVector.x + 10,
            this.positionVector.x - 10
        };
        int[] yPoly = {this.positionVector.y - 30,
            this.positionVector.y - 30,
            this.positionVector.y + 30,
            this.positionVector.y + 30
        };
        collider = new Polygon(xPoly, yPoly, xPoly.length);
        super.shape = collider;
        colliders = new HashMap<>();
    }

//<editor-fold defaultstate="collapsed" desc="Methods that need to be encapsulated in a role interface">
    private boolean mousePressed() {
        return !gamedata.getHeldMouse().isEmpty();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Attackable Implementation">
    @Override
    public int getShieldValue() {
        return shield;
    }

    @Override
    public int getHealthValue() {
        return health;
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
    public void notifyAnimationChangeListeners(AnimationBase animatorToRemove) {
        ActionEvent e = new ActionEvent(animatorToRemove, 0, "");
        for (ActionListener al : this.animationChangeListeners) {
            al.actionPerformed(e);
        }
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animators.put(state, animator);
        if (this.animator == null) {
            this.animator = animator;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Depletable Implementation">
    @Override
    public Integer getCount() {
        return this.lives;
    }

    @Override
    public void setCountDelta(Delta delta) {
        lives = delta.delta.intValue();
    }

    @Override
    public void addDepletableListener(ActionListener listener) {
        this.depletableListeners.add(listener);
    }

    @Override
    public void notifyDepletableListeners() {
        ActionEvent e = new ActionEvent(this, 0, null);
        for (ActionListener depletableListener : depletableListeners) {
            depletableListener.actionPerformed(e);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Archer Implementation">
    @Override
    public Integer getStrength() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStrengthDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFighterListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ready() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aim() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fire() {
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
        this.colliders.put(obj, direction);
    }

    @Override
    public void removeCollider(Collidable obj) {
        this.colliders.remove(obj);
    }

    @Override
    public HashMap<Collidable, CollisionPlane> getColliders() {
        return this.colliders;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Controllable Implementation">
    @Override
    public void processKeyInput() {

        if (this.gamedata.getHeldKeys().isEmpty()) {
            this.velocity = 0;
            this.rawVector.x = initialVelocity;
        }

        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_D)) {
            right = true;
            isFacingLeft = false;
            if (!mousePressed()) {
                removeAnimator = animator;
                animator = animators.get(AnimationState.WALKING_RIGHT);
                notifyAnimationChangeListeners(removeAnimator);
            }

            if (velocity < maxVelocity) {
                this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
                this.rawVector.x += this.velocity;
                this.velocityVector = VectorMath.getScaledVector(
                        VectorMath.getUnitVector(rawVector), velocity);
            }

        } else {
            right = false;
        }

        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_A)) {
            left = true;
            isFacingLeft = true;
            if (!mousePressed()) {
                removeAnimator = animator;
                animator = animators.get(AnimationState.WALKING_LEFT);
                notifyAnimationChangeListeners(removeAnimator);
            }
            if (velocity < maxVelocity) {

                this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
                this.rawVector.x -= this.velocity;
                this.velocityVector = VectorMath.getScaledVector(
                        VectorMath.getUnitVector(rawVector), velocity);
            }

        } else {
            left = false;
        }

        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_W)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.y -= this.velocity;
        }

        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_S)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.y += this.velocity;
        }

        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_SPACE)) {
            jump = true;
        } else {
            jump = false;
        }

        if (!(left || right)) {
            removeAnimator = animator;
            animator = isFacingLeft
                    ? animators.get(AnimationState.DEFAULT_LEFT)
                    : animators.get(AnimationState.DEFAULT_RIGHT);
            notifyAnimationChangeListeners(removeAnimator);
        }

        this.velocityVector = VectorMath.getVelocityVector(rawVector, velocity);
    }

    @Override
    public void processMouseInput() {

        if (this.gamedata.getHeldMouse().isEmpty()) {
            removeAnimator = animator;
            animator = isFacingLeft
                    ? animators.get(AnimationState.DEFAULT_LEFT)
                    : animators.get(AnimationState.DEFAULT_RIGHT);
            notifyAnimationChangeListeners(removeAnimator);
            //        if (canShootArrow()) {
            //            CharacterAnimationBase arrow = null;
            //            DoubleVector velocity = VectorMath.getVelocityVector(new DoubleVector(e.getX() - ar.PositionVector().getX(), e.getY() - ar.PositionVector().getY()), 15 + ar.getMouseCharge() * 3);
            //            //velocity = new DoubleVector(velocity.x + ar.getVelocityVector().x, velocity.y + ar.getVelocityVector().y); //Player Momentum transfers to arrow
            //            if (ar.animator == ar.animators.get(AnimationState.DEFAULT_RIGHT)) {
            //                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/ArrowRight.png");
            //            } else {
            //                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/ArrowLeft.png");
            //            }
            //            this.layeredPane.add(arrow, layeredPane.highestLayer());
            //            ar.shootArrow();
            //            ar.setMousePressed(false);
            //        } else {
        } else {
            Point mouseLocation = this.gamedata.getHeldMouse().get(1);

            if (mouseLocation == null) {
                return;
            }
            removeAnimator = animator;

            if (mouseLocation.getX() > this.positionVector.x) {
                animator = animators.get(AnimationState.SHOOTING_RIGHT);
            } else {
                animator = animators.get(AnimationState.SHOOTING_LEFT);
            }
            notifyAnimationChangeListeners(removeAnimator);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Movable Implementation">
    @Override
    public DoubleVector getVelocityVector() {
        return this.velocityVector;
    }

    @Override
    public void setVelocityScalarDelta(Delta delta
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Spawnable Implementation">
    @Override
    public Point getStartVector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point setStartVector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getRespawnTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRespawnTime(Integer seconds
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSafeTime(Integer seconds
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getSafeTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GameObject Implementation">
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
    protected void Update() {
        double velY = velocityVector.y;
        if (jump && canDoubleJump) {
            velY = -30;     //Stops Momentum and creats upwards momentup for double jump
            canDoubleJump = false;
        } else
            ;//velY += 3;  //Gravity

        if (right && !left) {
            this.velocityVector = VectorMath.getVelocityVector(new DoubleVector(1d, 0d), velocity);
        } else if (left && !right) {
            this.velocityVector = VectorMath.getVelocityVector(new DoubleVector(-1d, 0d), velocity);
        } else {
            this.velocityVector = new DoubleVector();
        }
        velocityVector.y = velY;
        this.positionVector.x += this.getVelocityVector().x;
        this.positionVector.y += this.getVelocityVector().y;

        this.collider = new Polygon();
        this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y - 30);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y - 30);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y + 30);
        this.collider.addPoint(this.positionVector.x - 10, this.positionVector.y + 30);
        super.shape = collider;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Collidable Interface">
    @Override
    public void notifyCollidableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCollided(boolean state
    ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCollided() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }
//</editor-fold>

}
