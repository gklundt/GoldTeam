package goldteam.characters;

import goldteam.domain.*;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * ArcherMan Proposal. Extends a game object and implements behavior interfaces
 * applicable to a Controllable Character.
 *
 * @author gordon
 */
public class BackgroundPanelGhost extends GameObject implements
        Animatable {

    private final Random random;

    private final Integer initialVelocity;
    private final Point initialPoint;
    private DoubleVector velocityVector;
    private final Integer velocity;
    private final DoubleVector rawVector;

    private AnimationBase animator;
    private final int dy;
    private final int dx;
    private int bounds;
    private boolean left;

    public BackgroundPanelGhost(GameEngine gameEngine, Point initialPoint) {
        super(gameEngine, initialPoint);
        this.random = new Random();

        this.initialPoint = initialPoint;
        this.initialVelocity = 20;
        this.positionVector = this.initialPoint;
        this.velocity = this.initialVelocity;
        this.rawVector = new DoubleVector(random.nextDouble() * 10, random.nextDouble() * 10);
        this.velocityVector = VectorMath.getVelocityVector(rawVector, this.velocity.doubleValue());
        this.dx = initialPoint.x;
        this.dy = initialPoint.y;
        this.bounds = 0;
        this.left = true;

    }

    @Override
    protected void Update() {

        this.positionVector.y = this.gamedata.getMapLocation().y + dy;

        if (left) {
            if (bounds <= -200) {
                left = false;
            } else {
                bounds -= 15;
            }
        } else {
            if (bounds >= 200) {
                left = true;
            } else {
                bounds += 15;
            }
        }

        this.positionVector.x = this.gamedata.getMapLocation().x + (dx + bounds);

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
    public void notifyAnimationChangeListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnimationBase getRemoveAnimator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
