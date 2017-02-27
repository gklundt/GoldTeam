package goldteam.characters;

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
 * ArcherMan Proposal.
 * Extends a game object and implements behavior interfaces applicable to
 * a Controllable Character.
 * @author gordon
 * @revised Rajiv
 */
public class ArcherMan extends GameObject implements 
        Attackable,     /* Shield and Health accessors */
        Animatable,     /* Animations */
        Brawler,        /* Strength accessor (from Fighter) and strike action */
        Archer,         /* Strength accessor (from Fighter) and ready/aim/fire routines */
        Collidable,     /* Information for Collision detection */
        Controllable,   /* Process Keyboard and Mouse events */
        Movable,        /* Vectors and scalar for movement */
        Spawnable,      /* Respawn details */
        Depletable      /* Life Counter */
{
    private final Double initialVelocity;
    private final ArrayList<ActionListener> attackableListeners;
    private final Double initialHealth;
    private final Double initialShield;
    private final Point initialPoint;
    private boolean right, left, jump, canDoubleJump, mousePressed;
    private DoubleVector velocityVector;
    private double velocity = 15d;
    private int charge;
    private int arrows;
    private Double health, shields;
    private static int lives = 3;
    //---Changed to public members to be accessed in implementing Panel------//
    public final DoubleVector rawVector;
    public AnimationBase animator;
    public final HashMap<AnimationState, AnimationBase> animators;
    public final ArrayList<ActionListener> animationChangeListeners;
    public AnimationBase removeAnimator;
    
    public ArcherMan(GameEngine gamedata, Point initialPoint)
    {
        super(gamedata, initialPoint);
        this.animationChangeListeners = new ArrayList<>();
        this.animators = new HashMap<>();
        this.initialPoint = initialPoint;
        this.initialVelocity = 10d;
        this.initialHealth = 5.0d;
        this.initialShield = 10.0d;
        velocityVector = new DoubleVector();
        canDoubleJump = true;
        mousePressed = false;
        this.positionVector = initialPoint;
        this.velocity = this.initialVelocity;
        this.rawVector = new DoubleVector(0d, 0d);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity);
        health = initialHealth;
        shields = initialShield;
        arrows = 10;
        
        attackableListeners = new ArrayList<>();
    }

    @Override
    public int getShieldValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHealthValue() {
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
    public DoubleVector getVelocityVector()
    {
        return this.velocityVector;
    }

    @Override
    public void setVelocityScalarDelta(Delta delta)
    {
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
    protected void GraphicsUpdateHandler()
    {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    protected void Update()
    {
        double velY = velocityVector.y;
        if(jump && canDoubleJump)
        {
            velY = -30;     //Stops Momentum and creats upwards momentup for double jump
            canDoubleJump = false;
        }
        else
            ;//velY += 3;  //Gravity
        
        if(right && ! left)
            this.velocityVector = VectorMath.getVelocityVector(new DoubleVector(1d, 0d), velocity);
        else if(left && !right)
            this.velocityVector = VectorMath.getVelocityVector(new DoubleVector(-1d, 0d), velocity);
        else
            this.velocityVector = new DoubleVector();
        velocityVector.y = velY;
        this.positionVector.x += this.getVelocityVector().x;
        this.positionVector.y += this.getVelocityVector().y;
        
        if(mousePressed)
            charge++;     
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
    
    public void setRight(boolean b)
    {
        right = b;
    }
    
    public void setLeft(boolean b)
    {
        left = b;
    }
    
    public void setJump(boolean b)
    {
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
        }    }

    @Override
    public void addAnimator(AnimationState state, AnimationBase animator) {
        this.animators.put(state, animator);
    }

    @Override
    public AnimationBase getRemoveAnimator() {
        return this.removeAnimator;
    }

    public void setMousePressed(boolean b)
    {
        mousePressed = b;
        if(!b)
            charge = 0;
    }
    
    public int getMouseCharge()
    {
        return charge;
    }
    
    public int getNumLives()
    {
        return lives;
    }
    
    public void die()
    {
        lives--;
    }
    
    public void shootArrow()
    {
        arrows--;
    }
    
    public boolean canShootArrow()
    {
        return arrows > 0;
    }
    
}
