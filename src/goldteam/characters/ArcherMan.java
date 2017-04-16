package goldteam.characters;

import goldteam.domain.*;
import goldteam.platforms.HorizontalPlatform;
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
        Collidable, /* Information for Collision detection */
        Controllable, /* Process Keyboard and Mouse events */
        Movable, /* Vectors and scalar for movement */
        Spawnable, /* Respawn details */
        Depletable, /* Life Counter */
        Boostable,
        Fallable {

    private int lives;

    private boolean right;
    private boolean left;
    private boolean jump;
    private boolean canDoubleJump;
    private boolean isFacingLeft;

    private final Double initialHealth;
    private final Double initialShield;
    private final Double initialVelocity;
    private final DoubleVector rawVector;
    private final double maxVelocity;

    private int health;
    private int shield;
    private int maxHealth;
    private int maxShield;

    private final ArrayList<ActionListener> animationChangeListeners;
    private final ArrayList<ActionListener> attackableListeners;
    private final ArrayList<ActionListener> depletableListeners;
    private final HashMap<AnimationState, AnimationBase> animators;
    private final HashMap<Collidable, CollisionPlane> colliders;
    private final ArrayList<ActionListener> boostableListeners;
    private final ArrayList<PlatformHelper> platformList;

    private double velocity;

    private int charge;
    private int debuffTime;
    private int arrows;

    private float speedModifier;
    private float jumpModifier;

    //---Changed to public members to be accessed in implementing Panel------//
    private AnimationBase animator;
    private AnimationBase removeAnimator;
    private final Polygon collider;

    private ArcherBow archerBow;

    private boolean isBoostableWeapon = false;
    private boolean isBoostableHealth = false;
    private boolean isPermanentBoostableWeapon = false;
    private final double gravity;
    private final double jumpVelocity;
    private boolean down;
    private final DoubleVector rightVector;
    private final DoubleVector leftVector;
    private final DoubleVector stationaryVector;
    private boolean isFalling;

    private enum JumpState {
        NONE, JUMP_1_RISING, JUMP_1_FALLING, JUMP_2_RISING, JUMP_2_FALLING
    };

    private enum YDir {
        UP, DOWN, STOPPED
    }

    private JumpState jumpstate;

    public ArcherMan(GameEngine gameData, Point initialPoint) {

        super(gameData, initialPoint);
        this.initialShield = 10.0d;
        this.initialHealth = 10.0d;
        this.lives = 10;

        this.rawVector = new DoubleVector(0d, 0d);
        this.leftVector = new DoubleVector(-1d, 0d);
        this.rightVector = new DoubleVector(1d, 0d);
        this.positionVector = initialPoint;
        this.initialVelocity = 10.0d;
        this.maxVelocity = 10d;
        this.speedModifier = 1.0f;
        this.jumpModifier = 1.0f;
        this.gravity = 20d;
        this.jumpVelocity = -30d;
        this.velocity = initialVelocity;
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity);
        this.isFalling = true;
        this.platformList = new ArrayList<>();
        jumpstate = JumpState.NONE;

        this.isFacingLeft = false;
        this.stationaryVector = new DoubleVector();
        this.animators = new HashMap<>();
        this.canDoubleJump = true;
        this.health = initialHealth.intValue();
        this.shield = initialShield.intValue();
        this.maxHealth = this.health;
        this.maxShield = this.shield;
        this.shape = new Polygon();
        this.collider = new Polygon();
        this.colliders = new HashMap<>();

        this.attackableListeners = new ArrayList<>();
        this.depletableListeners = new ArrayList<>();
        this.animationChangeListeners = new ArrayList<>();
        this.boostableListeners = new ArrayList<>();

        super.shape = collider;

    }

//<editor-fold defaultstate="collapsed" desc="Private parts">
    private boolean mousePressed() {
        return !gamedata.getHeldMouse().isEmpty();
    }

    private void setRight(boolean b) {
        right = b;
    }

    private void setLeft(boolean b) {
        left = b;
    }

    private void setJump(boolean b) {
        jump = b;
    }

    private void setMousePressed(boolean b) {
        if (!b) {
            charge = 0;
        }
    }

    private void setDown(boolean b) {
        down = b;
    }

    private int getMouseCharge() {
        return charge;
    }

    private int getNumLives() {
        return lives;
    }

    private void die() {
        lives--;
        resetJump();
        resetSpeed();
        this.maxHealth = this.initialHealth.intValue();
        this.maxShield = this.initialShield.intValue();
        this.health = this.maxHealth;
        this.shield = this.maxShield;
        //this.removeMe = true;
    }

    private void shootArrow() {
        arrows--;
    }

    private boolean canShootArrow() {
        return arrows > 0;
    }

    private int getLifeValue() {
        return lives;
    }

    private void setLifeValue(Delta delta) {
        lives = delta.delta.intValue();
    }

    public boolean getDown() {
        return down;
    }

    private class PlatformHelper {

        HorizontalPlatform platform;
        int timer;

        public PlatformHelper(HorizontalPlatform p, int t) {
            this.platform = p;
            this.timer = t;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods that need to be encapsulated in a role interface">
    public void setArcherBow(ArcherBow archerBow) {
        this.archerBow = archerBow;
    }

    public ArcherBow getArcherBow() {
        return this.archerBow;
    }

    public void specialFall(HorizontalPlatform pf) {
        this.startFalling();
        this.platformList.add(new PlatformHelper(pf, 25));
    }

    public boolean checkPlatformList(HorizontalPlatform hp) {
        for (PlatformHelper ph : platformList) {
            if (ph.platform == hp) {
                return true;
            }
        }
        return false;
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
        if (this.isBoostableHealth == false) {
            this.health += delta.delta;
            this.notifyAttackableListeners();
        } else {
            if (delta.delta > 0) {
                this.health += delta.delta;
                this.notifyAttackableListeners();
            }
        }
        if (this.health == 0) {
            die();
        }
    }

    @Override
    public void addAttackableListener(ActionListener listener) {
        this.attackableListeners.add(listener);
    }

    @Override
    public void notifyAttackableListeners() {

        for (ActionListener al : this.attackableListeners) {
            al.actionPerformed(null);
        }
    }

    @Override
    public double getChargeValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChargeDelta(Delta delta) {
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
        lives += delta.delta.intValue();
    }

    @Override
    public void addDepletableListener(ActionListener listener) {
        this.depletableListeners.add(listener);
    }

    @Override
    public void notifyDepletableListeners() {

        for (ActionListener depletableListener : depletableListeners) {
            depletableListener.actionPerformed(null);
        }
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
            //this.velocity = 0;
            this.rawVector.x = initialVelocity;
        }

        down = this.gamedata.getHeldKeys().contains(KeyEvent.VK_S);

        if (this.gamedata.getHeldKeys().contains(KeyEvent.VK_D)) {
            right = true;
            isFacingLeft = false;
            if (!mousePressed()) {
                removeAnimator = animator;
                animator = animators.get(AnimationState.WALKING_RIGHT);
                notifyAnimationChangeListeners(removeAnimator);
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
        } else {
            left = false;
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
    }

    @Override
    public void processMouseInput() {

        if (this.gamedata.getHeldMouse().isEmpty()) {
            removeAnimator = animator;
            animator = isFacingLeft
                    ? animators.get(AnimationState.DEFAULT_LEFT)
                    : animators.get(AnimationState.DEFAULT_RIGHT);
            notifyAnimationChangeListeners(removeAnimator);
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
        this.velocityVector.x += xDelta.delta;
        this.velocityVector.y += yDelta.delta;
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

        Double velY = velocityVector.y;
        Double velX = velocityVector.x;

        // Considered stopped and transition jump state
        double epsilon = .001d;
        boolean transitioned = false;
        if (velY > (-1d * epsilon) && velY < epsilon) {
            velY = 0d;
            if (this.jumpstate == JumpState.JUMP_2_FALLING && !transitioned) {
                this.jumpstate = JumpState.NONE;
                transitioned = true;
            }
            if (this.jumpstate == JumpState.JUMP_2_RISING && !transitioned) {
                this.jumpstate = JumpState.JUMP_2_FALLING;
                transitioned = true;
            }
            if (this.jumpstate == JumpState.JUMP_1_FALLING && !transitioned) {
                this.jumpstate = JumpState.NONE;
                transitioned = true;
            }
            if (this.jumpstate == JumpState.JUMP_1_RISING && !transitioned) {
                this.jumpstate = JumpState.JUMP_1_FALLING;
                transitioned = true;
            }
        }

        // Stopped by platforms started by jumping
        if (this.isFalling) {
            // let gravity work 
            System.out.println("Falling");
            velY = velY < gravity ? velY + 3 : gravity;
        } else {

            YDir ydir = YDir.STOPPED;
            if (velY > 0) {
                ydir = YDir.DOWN;
            }
            if (velY < 0) {
                ydir = YDir.UP;
            }

            // platform stopped gravity so continue to rise if needed
            velY = velY > 0 ? 0d : velY; // if dropping stop otherwise continue at rate
            velY = velY < gravity ? velY + 3 : gravity; // if falling slower than gravity fall faster until you reach gravity
            velY = velY < 0 ? velY + 3 : 0d; // if rising slow down otherwise stop
            switch (this.jumpstate) {
                case NONE:
                    System.out.println("Not Falling/Not Jumping");
                    switch (ydir) {
                        case UP:
                            // you were on your way up anyway for some reason
                            velY = velY < 0 ? velY + 3 : 0d; // if rising slow down otherwise stop
                            startFalling();
                            break;
                        case DOWN:
                            // stop downward velocity
                            velY = velY > 0 ? 0d : velY; // if dropping stop otherwise continue up at rate
                            break;
                        case STOPPED:
                            // stay stopped
                            break;
                        default:
                            throw new AssertionError(ydir.name());
                    }
                    break;
                case JUMP_1_RISING:
                    System.out.println("Not Falling/Jump 1 Rising");
                    switch (ydir) {
                        case UP:
                            velY = velY < 0 ? velY + 3 : 0d; // if rising slow down otherwise stop
                            startFalling();
                            break;
                        case DOWN:
                            // not really possible for this state
                            velY = velY > 0 ? 0d : velY; // if dropping stop otherwise continue up at rate
                            this.jumpstate = JumpState.JUMP_1_FALLING;
                            break;
                        case STOPPED:
                            // stay stopped and change state to none
                            this.jumpstate = JumpState.NONE;
                            break;
                        default:
                            throw new AssertionError(ydir.name());
                    }
                    break;
                case JUMP_1_FALLING:
                    System.out.println("Not Falling/Jump 1 Falling");
                    switch (ydir) {
                        case UP:
                            // not really possible in this state
                            velY = velY < 0 ? velY + 3 : 0d; // if rising slow down otherwise stop
                            startFalling();
                            break;
                        case DOWN:
                            // stop because you hit a platform and go back to none
                            velY = velY > 0 ? 0d : velY; // if dropping stop otherwise continue up at rate
                            this.jumpstate = JumpState.NONE;
                            break;
                        case STOPPED:
                            // stay stopped and change state to none
                            this.jumpstate = JumpState.NONE;
                            break;
                        default:
                            throw new AssertionError(ydir.name());
                    }
                    break;
                case JUMP_2_RISING:
                    System.out.println("Not Falling/Jump 2 Rising");
                    switch (ydir) {
                        case UP:
                            velY = velY < 0 ? velY + 3 : 0d; // if rising slow down otherwise stop
                            startFalling();
                            break;
                        case DOWN:
                            // not really possible for this state
                            velY = velY > 0 ? 0d : velY; // if dropping stop otherwise continue up at rate
                            this.jumpstate = JumpState.JUMP_2_FALLING;
                            break;
                        case STOPPED:
                            // stay stopped and change state to none
                            this.jumpstate = JumpState.NONE;
                            break;
                        default:
                            throw new AssertionError(ydir.name());
                    }
                    break;
                case JUMP_2_FALLING:
                    System.out.println("Not Falling/Jump 2 Falling");
                    switch (ydir) {
                        case UP:
                            // not really possible in this state
                            velY = velY < 0 ? velY + 3 : 0d; // if rising slow down otherwise stop
                            startFalling();
                            break;
                        case DOWN:
                            // stop because you hit a platform and go back to none
                            velY = velY > 0 ? 0d : velY; // if dropping stop otherwise continue up at rate
                            this.jumpstate = JumpState.NONE;
                            break;
                        case STOPPED:
                            // stay stopped and change state to none
                            this.jumpstate = JumpState.NONE;
                            break;
                        default:
                            throw new AssertionError(ydir.name());
                    }
                    break;
                default:
                    throw new AssertionError(this.jumpstate.name());
            }
        }

        // jump key is pressed
        if (jump) {
            jump = false;
            platformList.clear();

            switch (this.jumpstate) {
                case NONE:
                    velY = velY > jumpVelocity * jumpModifier ? jumpVelocity * jumpModifier : velY;
                    this.jumpstate = JumpState.JUMP_1_RISING;
                    this.startFalling();
                    break;
                case JUMP_1_RISING:
                    velY = velY > jumpVelocity * jumpModifier ? jumpVelocity * jumpModifier : velY;
                    this.jumpstate = JumpState.JUMP_2_RISING;
                    break;
                case JUMP_1_FALLING:
                    velY = velY > jumpVelocity * jumpModifier ? jumpVelocity * jumpModifier : velY;
                    this.jumpstate = JumpState.JUMP_2_RISING;
                    break;
                case JUMP_2_RISING:
                    break;
                case JUMP_2_FALLING:
                    break;
                default:
                    throw new AssertionError(this.jumpstate.name());
            }
        }

        if (platformList != null) {
            for (int i = 0; i < platformList.size(); i++) {
                PlatformHelper ph = platformList.get(i);
                ph.timer--;
                if (ph.timer == 0) {
                    platformList.remove(ph);
                    i--;
                }
            }
        }

        debuffTime--;
        if (debuffTime == 0) {
            this.resetBuffs();
        }

        if (right && !left) {
            velX = velocity * speedModifier;
        } else if (left && !right) {
            velX = -1 * (velocity * speedModifier);
        } else {
            velX = 0d;
        }

        // Edge Check
        if (this.positionVector.x < 0) {
            velX = 0d;
            this.positionVector.x = 0;
        }
        if (this.positionVector.x > this.gamedata.getVisibleDimensions().width) {
            velX = 0d;
            this.positionVector.x = this.gamedata.getVisibleDimensions().width;
        }
        if (this.positionVector.y < 0) {
            velY = 0d;
            this.positionVector.y = 0;
        }
        if (this.positionVector.y > this.gamedata.getVisibleDimensions().height) {
            velY = 0d;
            this.positionVector.y = this.gamedata.getVisibleDimensions().height;
        }

        velocityVector.y = velY;
        velocityVector.x = velX;

        this.positionVector.x += this.getVelocityVector().x;
        this.positionVector.y += this.getVelocityVector().y;

        this.collider.reset();
        this.collider.addPoint(this.positionVector.x, this.positionVector.y);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y);
        this.collider.addPoint(this.positionVector.x + 10, this.positionVector.y + 60);
        this.collider.addPoint(this.positionVector.x, this.positionVector.y + 60);

        super.shape = collider;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Collidable Interface">
    @Override
    public void notifyCollidableListeners() {
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

    public void debuff() {
        debuffSpeed();
        debuffJump();
        debuffTime = 120;
    }

    public void buff() {
        buffSpeed();
        buffJump();
    }

    private void resetBuffs() {
        resetSpeed();
        resetJump();
    }

    private void debuffSpeed() {
        speedModifier = 0.5f;
    }

    private void buffSpeed() {
        speedModifier = 1.25f;
    }

    private void resetSpeed() {
        speedModifier = 1.0f;
    }

    private void debuffJump() {
        jumpModifier = 0.75f;
    }

    private void buffJump() {
        jumpModifier = 1.1f;
    }

    private void resetJump() {
        jumpModifier = 1.0f;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Boostable Interface">
    @Override
    public void setBoostableWeapon(boolean boostable) {
        this.isBoostableWeapon = boostable;
        this.notifyBoostableListeners();
    }

    @Override
    public boolean isBoostableWeapon() {
        return this.isBoostableWeapon;
    }

    @Override
    public void setBoostableHealth(boolean boostable) {
        this.isBoostableHealth = boostable;
        this.notifyBoostableListeners();
    }

    @Override
    public boolean isBoostableHealth() {
        return this.isBoostableHealth;
    }

    @Override
    public void setPermanentBoostableWeapon(boolean boostable) {
        this.isPermanentBoostableWeapon = boostable;
        this.notifyBoostableListeners();
    }

    @Override
    public boolean isPermanentBoostableWeapon() {
        return this.isPermanentBoostableWeapon;
    }

    @Override
    public void addBoostableListener(ActionListener listener) {
        this.boostableListeners.add(listener);
    }

    @Override
    public void notifyBoostableListeners() {

        for (ActionListener al : this.boostableListeners) {
            al.actionPerformed(null);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Fallable Interface">
    @Override
    public void startFalling() {
        this.isFalling = true;
    }

    @Override
    public void stopFalling() {
        this.isFalling = false;
    }
//</editor-fold>
}
