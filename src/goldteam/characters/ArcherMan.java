package goldteam.characters;

import goldteam.animators.ArcherAnimation;
import goldteam.domain.*;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ArcherMan Proposal. Extends a game object and implements behavior interfaces
 * applicable to a Controllable Character.
 *
 * @author gordon
 * @revised Rajiv
 * @revised Caleb Dunham
 */
public class ArcherMan extends GameObject implements
        Attackable, /* Shield and Health accessors */
        Animatable, /* Animations */
        Brawler, /* Strength accessor (from Fighter) and strike action */
        Archer, /* Strength accessor (from Fighter) and ready/aim/fire routines */
        Collidable, /* Information for Collision detection */
        Controllable, /* Process Keyboard and Mouse events */
        Movable, /* Vectors and scalar for movement */
        Spawnable, /* Respawn details */
        Depletable /* Life Counter */ {

    private Double initialVelocity;
    private final ArrayList<ActionListener> attackableListeners;
    private Double initialHealth;
    private Double initialShield;
    private Point initialPoint;
    private int initialArrows;
    private boolean right, left, jump, canDoubleJump, mousePressed;
    private DoubleVector velocityVector;
    private double velocity;
    private int charge;
    private int arrows;
    private int health, shield;
    private static int lives = 3;
    //---Changed to public members to be accessed in implementing Panel------//
    public final DoubleVector rawVector;
    public AnimationBase animator;
    public final HashMap<AnimationState, AnimationBase> animators;
    public final ArrayList<ActionListener> animationChangeListeners;
    public AnimationBase removeAnimator;

    public ArcherMan(GameEngine gameData, Point initialPoint) {
        super(gameData, initialPoint);
        this.initialPoint = initialPoint;
        this.animators = new HashMap<>();
        this.rawVector = new DoubleVector(0d, 0d);
        this.velocityVector = new DoubleVector();
        this.attackableListeners = new ArrayList<>();
        this.animationChangeListeners = new ArrayList<>();
        this.canDoubleJump = true;
        this.mousePressed = false;

        init();

        this.arrows = initialArrows;
        this.health = initialHealth.intValue();
        this.shield = initialShield.intValue();
        this.velocity = initialVelocity;
        this.positionVector = initialPoint;
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity);
        
        int [] xPoly = {this.positionVector.x - 10, 
                        this.positionVector.x + 10, 
                        this.positionVector.x + 10,
                        this.positionVector.x - 10
        };
        int [] yPoly = {this.positionVector.y - 10, 
                        this.positionVector.y - 10,
                        this.positionVector.y + 10,
                        this.positionVector.y + 10
        };
        this.shape = new Polygon(xPoly, yPoly, xPoly.length);
    }

    private void init() {
        this.initialVelocity = 10d;
        this.initialHealth = 5.0d;
        this.initialShield = 10.0d;
        this.initialArrows = 100;
        CharacterAnimationBase archerDefaultRight = new ArcherAnimation(this, gamedata.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png", charge, charge);
        CharacterAnimationBase archerDefaultLeft = new ArcherAnimation(this, gamedata.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png", charge, charge);
        CharacterAnimationBase archerWalkingRight = new ArcherAnimation(this, gamedata.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png", charge);
        CharacterAnimationBase archerWalkingLeft = new ArcherAnimation(this, gamedata.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png", charge);
        CharacterAnimationBase archerDrawingRight = new ArcherAnimation(this, gamedata.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png", charge);
        CharacterAnimationBase archerDrawingLeft = new ArcherAnimation(this, gamedata.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png", charge);
        this.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
        this.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
        this.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
        this.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
        this.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
        this.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
        this.setAnimator(archerDefaultRight);
    }

    @Override
    public int getShieldValue() {
        return shield;
    }

    @Override
    public int getHealthValue() {
        return health;
    }

    @Override
    public int getArrowCount() {
        return this.arrows;
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
    public void setArrowDelta(Delta delta) {
        this.arrows = delta.delta.intValue();
    }

    @Override
    public void strike() {
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

    @Override
    public Integer getStrength() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStrengthDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Polygon getPolygon() {
        return this.shape;
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
    public void processKeyInput(KeyEvent keyEvent) {
        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_D)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.x += this.velocity;
        }
        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_A)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.x -= this.velocity;
        }
        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_W)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.y -= this.velocity;
        }
        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_S)) {
            this.velocity = this.velocity > this.initialVelocity - .5 ? this.velocity + .5 : this.initialVelocity;
            this.rawVector.y += this.velocity;
        }
        this.velocityVector = VectorMath.getVelocityVector(rawVector, velocity);
    }

    @Override
    public void processMouseInput(MouseEvent mouseEvent) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    public void setRespawnTime(Integer seconds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSafeTime(Integer seconds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getSafeTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCountDelta(Delta delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void addFighterListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDepletableListener(ActionListener listener) {
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

        if (mousePressed) {
            charge++;
        }
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
    public void addAnimationTimerListener(ActionListener listener) {
        this.gamedata.addAnimationUpdateTimerListener(listener);
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setJump(boolean b) {
        jump = b;
    }

    @Override
    public void addAnimationChangeListener(ActionListener listener) {
        this.animationChangeListeners.add(listener);
    }

    @Override
    public void notifyAnimationChangeListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.animationChangeListeners) {
            al.actionPerformed(e);
        }
    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animators.put(state, animator);
    }

    @Override
    public AnimationBase getRemoveAnimator() {
        return this.removeAnimator;
    }

    public void setMousePressed(boolean b) {
        mousePressed = b;
        if (!b) {
            charge = 0;
        }
    }

    public int getMouseCharge() {
        return charge;
    }

    public int getNumLives() {
        return lives;
    }

    public void die() {
        lives--;
    }

    public void shootArrow() {
        arrows--;
    }

    public boolean canShootArrow() {
        return arrows > 0;
    }

    @Override
    public int getLifeValue() {
        return lives;
    }

    @Override
    public void setLifeValue(Delta delta) {
        lives = delta.delta.intValue();
    }

    @Override
    public void notifyAttackableListeners() {
        ActionEvent e = new ActionEvent(this, 0, "");
        for (ActionListener al : this.attackableListeners) {
            al.actionPerformed(e);
        }
    }

    @Override
    public void notifyDepletableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyCollidableListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
